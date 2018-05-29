package com.disharth.highlighter.domain;

import java.util.HashMap;
import java.util.Map;

public class Relationship {

    private final int id;
    private final String name;
    private Map<Integer,String> relationshipType = new HashMap<>();


    public Relationship(int id, String name ) {
        this.id = id;
        this.name = name;

    }

    // has to be : seperated string e.g. 4:* this means entity with id 4 had many type relationship.
    public void setRelationshipType(String rel){
        String[] split = rel.split(":");
        Integer id = Integer.valueOf(split[0]);
        String type = split[1];
        relationshipType.put(id , type);
    }

    public String getType(int entityId){
        return relationshipType.get(entityId);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Map<Integer, String> getRelationshipType() {
        return relationshipType;
    }
}
