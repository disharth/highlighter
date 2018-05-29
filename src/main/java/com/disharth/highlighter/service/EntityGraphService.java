package com.disharth.highlighter.service;

import com.disharth.highlighter.domain.RelationshipEdge;
import com.disharth.highlighter.repository.EntityGraphRepository;

import java.util.List;

public class EntityGraphService {

    private EntityGraphRepository repository = new EntityGraphRepository();


    public List<RelationshipEdge> generateRelationshipGraph(int[] entities){
        return repository.generateRelationshipGraph(entities);
    }

}
