package common.pojo;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;

/**
 * 自定义响应结构
 */
public class NetstoreResult {

    //定义jackson对象
    private static  final ObjectMapper MAPPER=new ObjectMapper();

    //响应业务状态
    private Integer status;

    //响应消息
    private String msg;

    //响应中的数据
    private Object data;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public NetstoreResult(){

    }

    public NetstoreResult(Integer status, String msg, Object data) {

        this.status=status;
        this.data=data;
        this.msg=msg;
    }

    public NetstoreResult(Object data) {
        this.status=200;
        this.msg="OK";
        this.data=data;
    }

    public static  NetstoreResult build(Integer status,String msg,Object data){

        return new NetstoreResult(status,msg,data);
    }

    public static  NetstoreResult ok(Object data){

        return  new NetstoreResult(data);
    }

    public static NetstoreResult ok(){
        return new NetstoreResult(null);
    }

    /**将json结果集转化为netstoreResult对象
     *
     */
    public static  NetstoreResult formatToPojo(String jsonData,Class<?> clazz){

        if (clazz ==null){
            try {
                return MAPPER.readValue(jsonData,NetstoreResult.class);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        JsonNode jsonNode= null;
        try {
            jsonNode = MAPPER.readTree(jsonData);
        } catch (IOException e) {
            e.printStackTrace();
        }
        JsonNode data=jsonNode.get("data");
        Object obj=null;
        if (clazz!=null){
            if (data.isObject()){
                try {
                    obj=MAPPER.readValue(data.traverse(),clazz);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else if (data.isTextual()){
                try {
                    obj=MAPPER.readValue(data.asText(),clazz);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return  build(jsonNode.get("status").intValue(),jsonNode.get("msg").asText(),obj);
    }


    /**
     * 没有object对象的转化
     * @param json
     * @return
     */
    public static  NetstoreResult format(String json){

        try {
            return MAPPER.readValue(json,NetstoreResult.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return  null;
    }

    public  static  NetstoreResult formatToList(String jsonData,Class<?> clazz){

        try {
            JsonNode jsonNode=MAPPER.readTree(jsonData);
            JsonNode data=jsonNode.get("data");
            Object obj=null;
            if(data.isArray()&&data.size()>0){
                obj=MAPPER.readValue(data.traverse(),
                        MAPPER.getTypeFactory().constructCollectionType(List.class,clazz));
            }
            return build(jsonNode.get("status").intValue(),jsonNode.get("msg").asText(),obj);
        } catch (IOException e) {
            e.printStackTrace();
            return  null;
        }



    }


}
