package com.disharth.highlighter.repository;

import com.disharth.highlighter.domain.Entity;
import com.disharth.highlighter.domain.Relationship;
import edu.princeton.cs.algs4.In;

import java.util.HashMap;
import java.util.Map;

public class DataCache {
    private Map<Integer, Entity> entityMap =  new HashMap<>();
    private Map<Integer, Relationship> relMap =  new HashMap<>();

    public DataCache(){
        loadEntities();
        loadRelationships();
    }

    private Map<Integer , Entity> loadEntities(){
        In input = new In("entities.txt");
        while (input.hasNextLine()){
            int id = input.readInt();
            String name = input.readString();
            entityMap.put(id,new Entity(id,name));

        }
        return entityMap;
    }

    private Map<Integer , Relationship> loadRelationships(){
        In input = new In("rel_with reltypeinfo.txt");
        while (input.hasNextLine()){

            int id = input.readInt();
            String name = input.readString();
            Relationship relationship = new Relationship(id, name);
            String firstRelType = input.readString();
            String secondRelType = input.readString();
            relationship.setRelationshipType(firstRelType);
            relationship.setRelationshipType(secondRelType);
            relMap.put(id,relationship);

        }
        return relMap;
    }

    public Map<Integer,Entity> getEntityMap(){
        return entityMap;
    }

    public Map<Integer, Relationship> getRelMap(){
        return relMap;
    }
}
