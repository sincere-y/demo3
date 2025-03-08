package ${package.Service};
import ${package.Entity}.${entity};
import ${package.Mapper}.${table.mapperName};
import ${package.Service}.${table.serviceName};
import ${superServiceImplClassPackage};
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.math.BigInteger;

@Service

 public class ${table.serviceName} {
 @Resource
 private ${table.mapperName} mapper;

 public ${entity} getById(BigInteger id) {
 return mapper.getById(id);
 }

 public ${entity} extractById(BigInteger id) {
 return mapper.extractById(id);
 }


 public int insert(${entity} ${entity?uncap_first}){

 return mapper.insert(${entity?uncap_first});
 }


 public int update(${entity} ${entity?uncap_first}){

 return mapper.update(${entity?uncap_first});

 }


 public int delete(BigInteger id) {
 return mapper.delete(id, (int) (System.currentTimeMillis() / 1000));
 }
 }



