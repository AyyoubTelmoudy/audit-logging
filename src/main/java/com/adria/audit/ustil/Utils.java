package com.adria.audit.ustil;


import com.adria.audit.event.Attribute;
import com.adria.audit.event.AttributeType;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.*;
import java.util.*;


public class Utils {

    public static List<Attribute> getInputOutputFlux(Object[] input, Object output) throws IllegalAccessException {
        List<Attribute> attributes=new ArrayList<>();
        Utils.getInputAttributes(input,attributes);
        Utils.getOutputAttributes(output,attributes);
        return attributes;
    }

    private static void getInputAttributes(Object object,List<Attribute> attributes) throws IllegalAccessException {
        if(object!=null&&attributes!=null)
        {
            if(object instanceof Collection<?> || object.getClass().isArray())
            {
                if(object instanceof Collection<?> )
                {
                    for (Object obj:(Collection)object)
                    {
                        getInputAttributes(obj,attributes);
                    }
                }
                else
                {
                    for (Object obj:(Object[])object)
                    {
                        getInputAttributes(obj,attributes);
                    }
                }
            }
            else
            {
                if(object instanceof Map<?,?>)
                {
                    for (Object obj:((Map<?, ?>) object).keySet())
                    {
                        if(Utils.returnStringOrNullOfValue(((Map<?, ?>) object).get(obj))==null)
                        {
                            getInputAttributes(((Map<?, ?>) object).get(obj),attributes);
                        }
                        else
                        {
                            Attribute attribute=new Attribute();
                            attribute.setKey(Utils.returnStringOrNullOfKey(obj));
                            attribute.setValue(Utils.returnStringOrNullOfValue(((Map<?, ?>) object).get(obj)));
                            attribute.setType(AttributeType.INPUT);
                            attributes.add(attribute);
                        }

                    }
                }
                else
                {
                    switch(object.getClass().getName()) {
                        case "java.lang.String": addAttribute(null,(String) object,AttributeType.INPUT,attributes);break;
                        case "java.math.BigInteger": addAttribute(null, String.valueOf((BigInteger) object),AttributeType.INPUT,attributes); break;
                        case "java.math.BigDecimal": addAttribute(null, String.valueOf((BigDecimal) object),AttributeType.INPUT,attributes);break;
                        case "java.util.Date":addAttribute(null, String.valueOf((Date)object),AttributeType.INPUT,attributes);break;
                        case "java.util.Calendar":addAttribute(null, String.valueOf((Calendar)object),AttributeType.INPUT,attributes); break;
                        case "java.time.LocalDate":addAttribute(null, String.valueOf((LocalDate)object),AttributeType.INPUT,attributes);break;
                        case "java.time.LocalTime":addAttribute(null, String.valueOf((LocalTime)object),AttributeType.INPUT,attributes);break;
                        case "java.time.LocalDateTime":addAttribute(null, String.valueOf((LocalDateTime)object),AttributeType.INPUT,attributes);break;
                        case "java.time.ZonedDateTime":addAttribute(null, String.valueOf((ZonedDateTime)object),AttributeType.INPUT,attributes); break;
                        case "java.time.Duration":addAttribute(null, String.valueOf((Duration)object),AttributeType.INPUT,attributes);break;
                        case "java.time.Period":addAttribute(null, String.valueOf((Period)object),AttributeType.INPUT,attributes);break;
                        case "java.lang.Byte":addAttribute(null, String.valueOf((Byte)object),AttributeType.INPUT,attributes);break;
                        case "byte":addAttribute(null, String.valueOf((byte)object),AttributeType.INPUT,attributes);break;
                        case "java.lang.Short":addAttribute(null, String.valueOf((Short)object),AttributeType.INPUT,attributes);break;
                        case "short":addAttribute(null, String.valueOf((short)object),AttributeType.INPUT,attributes);break;
                        case "java.lang.Integer":addAttribute(null, String.valueOf((Integer) object),AttributeType.INPUT,attributes);break;
                        case "int":addAttribute(null, String.valueOf((int)object),AttributeType.INPUT,attributes);break;
                        case "java.lang.Long":addAttribute(null, String.valueOf((Long) object),AttributeType.INPUT,attributes);break;
                        case "long":addAttribute(null, String.valueOf((long)object),AttributeType.OUTPUT,attributes);break;
                        case "java.lang.Float":addAttribute(null, String.valueOf((Float) object),AttributeType.INPUT,attributes);break;
                        case "float":addAttribute(null, String.valueOf((float)object),AttributeType.INPUT,attributes); break;
                        case "java.lang.Double":addAttribute(null, String.valueOf((Double) object),AttributeType.INPUT,attributes);break;
                        case "double":addAttribute(null, String.valueOf((double)object),AttributeType.INPUT,attributes);break;
                        case "java.lang.Boolean":addAttribute(null, String.valueOf((Boolean) object),AttributeType.INPUT,attributes);break;
                        case "boolean":addAttribute(null, String.valueOf((Boolean) object),AttributeType.INPUT,attributes);break;
                        case "java.lang.Character":addAttribute(null, String.valueOf((Character) object),AttributeType.INPUT,attributes);break;
                        case "char":addAttribute(null, String.valueOf((char)object),AttributeType.INPUT,attributes);break;
                        default:
                            for (Field field:object.getClass().getDeclaredFields())
                            {
                                field.setAccessible(true);
                                Attribute attribute=new Attribute();
                                attribute.setKey(field.getName());
                                attribute.setValue(field.get(object).toString());
                                attribute.setType(AttributeType.INPUT);
                                attributes.add(attribute);
                            }
                            break;
                    }
                }
            }

        }
    }

    private static void getOutputAttributes(Object object,List<Attribute> attributes) throws IllegalAccessException {
        if(object!=null&&attributes!=null)
        {
                if(object instanceof Collection<?> || object.getClass().isArray())
                {
                    if(object instanceof Collection<?> )
                    {
                        for (Object obj:(Collection)object)
                        {
                            getOutputAttributes(obj,attributes);
                        }
                    }
                    else
                    {
                        for (Object obj:(Object[])object)
                        {
                            getOutputAttributes(obj,attributes);
                        }
                    }
                }
                else
                {
                    if(object instanceof Map<?,?>)
                    {
                        for (Object obj:((Map<?, ?>) object).keySet())
                        {
                            if(Utils.returnStringOrNullOfValue(((Map<?, ?>) object).get(obj))==null)
                            {
                                getOutputAttributes(((Map<?, ?>) object).get(obj),attributes);
                            }
                            else
                            {
                                Attribute attribute=new Attribute();
                                attribute.setKey(Utils.returnStringOrNullOfKey(obj));
                                attribute.setValue(Utils.returnStringOrNullOfValue(((Map<?, ?>) object).get(obj)));
                                attribute.setType(AttributeType.OUTPUT);
                                attributes.add(attribute);
                            }
                        }
                    }
                    else
                    {
                        switch(object.getClass().getName()) {
                            case "java.lang.String": addAttribute(null,(String) object,AttributeType.OUTPUT,attributes);break;
                            case "java.math.BigInteger": addAttribute(null, String.valueOf((BigInteger) object),AttributeType.OUTPUT,attributes); break;
                            case "java.math.BigDecimal": addAttribute(null, String.valueOf((BigDecimal) object),AttributeType.OUTPUT,attributes);break;
                            case "java.util.Date":addAttribute(null, String.valueOf((Date)object),AttributeType.OUTPUT,attributes);break;
                            case "java.util.Calendar":addAttribute(null, String.valueOf((Calendar)object),AttributeType.OUTPUT,attributes); break;
                            case "java.time.LocalDate":addAttribute(null, String.valueOf((LocalDate)object),AttributeType.OUTPUT,attributes);break;
                            case "java.time.LocalTime":addAttribute(null, String.valueOf((LocalTime)object),AttributeType.OUTPUT,attributes);break;
                            case "java.time.LocalDateTime":addAttribute(null, String.valueOf((LocalDateTime)object),AttributeType.OUTPUT,attributes);break;
                            case "java.time.ZonedDateTime":addAttribute(null, String.valueOf((ZonedDateTime)object),AttributeType.OUTPUT,attributes); break;
                            case "java.time.Duration":addAttribute(null, String.valueOf((Duration)object),AttributeType.OUTPUT,attributes);break;
                            case "java.time.Period":addAttribute(null, String.valueOf((Period)object),AttributeType.OUTPUT,attributes);break;
                            case "java.lang.Byte":addAttribute(null, String.valueOf((Byte)object),AttributeType.OUTPUT,attributes);break;
                            case "byte":addAttribute(null, String.valueOf((byte)object),AttributeType.OUTPUT,attributes);break;
                            case "java.lang.Short":addAttribute(null, String.valueOf((Short)object),AttributeType.OUTPUT,attributes);break;
                            case "short":addAttribute(null, String.valueOf((short)object),AttributeType.OUTPUT,attributes);break;
                            case "java.lang.Integer":addAttribute(null, String.valueOf((Integer) object),AttributeType.OUTPUT,attributes);break;
                            case "int":addAttribute(null, String.valueOf((int)object),AttributeType.OUTPUT,attributes);break;
                            case "java.lang.Long":addAttribute(null, String.valueOf((Long) object),AttributeType.OUTPUT,attributes);break;
                            case "long":addAttribute(null, String.valueOf((long)object),AttributeType.OUTPUT,attributes);break;
                            case "java.lang.Float":addAttribute(null, String.valueOf((Float) object),AttributeType.OUTPUT,attributes);break;
                            case "float":addAttribute(null, String.valueOf((float)object),AttributeType.OUTPUT,attributes); break;
                            case "java.lang.Double":addAttribute(null, String.valueOf((Double) object),AttributeType.OUTPUT,attributes);break;
                            case "double":addAttribute(null, String.valueOf((double)object),AttributeType.OUTPUT,attributes);break;
                            case "java.lang.Boolean":addAttribute(null, String.valueOf((Boolean) object),AttributeType.OUTPUT,attributes);break;
                            case "boolean":addAttribute(null, String.valueOf((Boolean) object),AttributeType.OUTPUT,attributes);break;
                            case "java.lang.Character":addAttribute(null, String.valueOf((Character) object),AttributeType.OUTPUT,attributes);break;
                            case "char":addAttribute(null, String.valueOf((char)object),AttributeType.OUTPUT,attributes);break;
                            default:
                                for (Field field:object.getClass().getDeclaredFields())
                                {
                                    field.setAccessible(true);
                                    Attribute attribute=new Attribute();
                                    attribute.setKey(field.getName());
                                    attribute.setValue(field.get(object).toString());
                                    attribute.setType(AttributeType.OUTPUT);
                                    attributes.add(attribute);
                                }
                                break;
                        }
                    }
                }

        }
    }

    private static void addAttribute(String key,String value,AttributeType attributeType,List<Attribute> attributes)
    {
       attributes.add(new Attribute(key,value,attributeType));
    }
    private static String returnStringOrNullOfValue(Object object)
    {
        switch(object.getClass().getName()) {
            case "java.lang.String": return (String) object;
            case "java.math.BigInteger": return String.valueOf((BigInteger) object);
            case "java.math.BigDecimal": return String.valueOf((BigDecimal) object);
            case "java.util.Date":return String.valueOf((Date)object);
            case "java.util.Calendar":return String.valueOf((Calendar)object);
            case "java.time.LocalDate":return String.valueOf((LocalDate)object);
            case "java.time.LocalTime":return String.valueOf((LocalTime)object);
            case "java.time.LocalDateTime":return String.valueOf((LocalDateTime)object);
            case "java.time.ZonedDateTime":return String.valueOf((ZonedDateTime)object);
            case "java.time.Duration":return String.valueOf((Duration)object);
            case "java.time.Period":return String.valueOf((Period)object);
            case "java.lang.Byte":return String.valueOf((Byte)object);
            case "byte":return String.valueOf((byte)object);
            case "java.lang.Short":return String.valueOf((Short)object);
            case "short":return String.valueOf((short)object);
            case "java.lang.Integer":return String.valueOf((Integer) object);
            case "int":return String.valueOf((int)object);
            case "java.lang.Long":return String.valueOf((Long) object);
            case "long": return String.valueOf((long)object);
            case "java.lang.Float":return String.valueOf((Float) object);
            case "float":return String.valueOf((float)object);
            case "java.lang.Double":return String.valueOf((Double) object);
            case "double":return String.valueOf((double)object);
            case "java.lang.Boolean":return String.valueOf((Boolean) object);
            case "boolean":return String.valueOf((Boolean) object);
            case "java.lang.Character":return String.valueOf((Character) object);
            case "char":return String.valueOf((char)object);
            default:return null;
        }
    }

    private static String returnStringOrNullOfKey(Object object)
    {
        switch(object.getClass().getName()) {
            case "java.lang.String": return (String) object;
            case "java.math.BigInteger": return String.valueOf((BigInteger) object);
            case "java.math.BigDecimal": return String.valueOf((BigDecimal) object);
            case "java.util.Date":return String.valueOf((Date)object);
            case "java.util.Calendar":return String.valueOf((Calendar)object);
            case "java.time.LocalDate":return String.valueOf((LocalDate)object);
            case "java.time.LocalTime":return String.valueOf((LocalTime)object);
            case "java.time.LocalDateTime":return String.valueOf((LocalDateTime)object);
            case "java.time.ZonedDateTime":return String.valueOf((ZonedDateTime)object);
            case "java.time.Duration":return String.valueOf((Duration)object);
            case "java.time.Period":return String.valueOf((Period)object);
            case "java.lang.Byte":return String.valueOf((Byte)object);
            case "byte":return String.valueOf((byte)object);
            case "java.lang.Short":return String.valueOf((Short)object);
            case "short":return String.valueOf((short)object);
            case "java.lang.Integer":return String.valueOf((Integer) object);
            case "int":return String.valueOf((int)object);
            case "java.lang.Long":return String.valueOf((Long) object);
            case "long": return String.valueOf((long)object);
            case "java.lang.Float":return String.valueOf((Float) object);
            case "float":return String.valueOf((float)object);
            case "java.lang.Double":return String.valueOf((Double) object);
            case "double":return String.valueOf((double)object);
            case "java.lang.Boolean":return String.valueOf((Boolean) object);
            case "boolean":return String.valueOf((Boolean) object);
            case "java.lang.Character":return String.valueOf((Character) object);
            case "char":return String.valueOf((char)object);
            default:return object.toString();
        }
    }


}
