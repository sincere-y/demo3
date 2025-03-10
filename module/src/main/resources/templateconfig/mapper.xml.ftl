<?xml version="1.0" encoding="UTF-8" ?>

<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${package.Mapper}.${table.mapperName}">


    <insert id="insert" parameterType="${package.Entity}.${entity}">
        insert into ${table.name} (
        <#list table.fields as field>
            <#if field.propertyType == "Integer"||field.propertyType == "Byte"||field.propertyType == "BigInteger"||field.propertyType == "Long">

                <if test="${table.name}.${field.propertyName} != null ">${field.name}<#if field_has_next>,</#if></if>
            <#else>
                <if test="${table.name}.${field.propertyName} != null and ${table.name}.${field.propertyName} !=''">${field.name}<#if field_has_next>,</#if></if>

            </#if>        </#list>
        )
        values (
        <#list table.fields as field>
            <#if field.propertyType == "Integer"||field.propertyType == "Byte"||field.propertyType == "BigInteger"||field.propertyType == "Long">

                <if test="${table.name}.${field.propertyName} != null ">${field.name}<#if field_has_next>,</#if></if>
            <#else>
                <if test="${table.name}.${field.propertyName} != null and ${table.name}.${field.propertyName} !=''">${field.name}<#if field_has_next>,</#if></if>

            </#if>
        </#list>
        )
    </insert>


    <update id="update" parameterType="${package.Entity}.${entity}">
        update ${table.name}
        <set>
        <#list table.fields as field>
            <#if field.propertyType == "Integer"||field.propertyType == "Byte"||field.propertyType == "BigInteger"||field.propertyType == "Long">

                <if test="${table.name}.${field.propertyName} != null ">${field.name}<#if field_has_next>,</#if></if>
            <#else>
                <if test="${table.name}.${field.propertyName} != null and ${table.name}.${field.propertyName} !=''">${field.name}<#if field_has_next>,</#if></if>

            </#if>
        </#list>
        </set>
        where id = <#noparse>#{</#noparse>${table.name}.id<#noparse>}</#noparse>
    </update>


</mapper>

