def connParams = [broker: "192.168.1.105:426", domain: "INCHARGE-SA", password: "rcpass", username: "admin"];
SmartsDatasource.execute(connParams){SmartsDatasource ds->
    def attributes = ds.getAllProperties("Host", "ifountai-qj")
    attributes.each{key,val->
        println "key:${key} val:${val}"
    }
}