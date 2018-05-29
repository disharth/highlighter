package com.disharth.highlighter.repository;

import com.disharth.highlighter.domain.RelationshipEdge;
import edu.princeton.cs.algs4.In;

import java.util.*;

public class EntityGraphRepository {

    private final EntityGraph graph;

    public EntityGraphRepository(){
        this.graph = createGraph();
    }



    public List<RelationshipEdge> generateRelationshipGraph(int[] setOfEntityIds ){

        Set<Integer> currentSet = new TreeSet();
        currentSet.add(Integer.valueOf(setOfEntityIds[0]));
        List<RelationshipEdge> edges = new ArrayList<>();
        for (int i = 1; i <setOfEntityIds.length ; i++) {
            int val = Integer.valueOf(setOfEntityIds[i]);
            for (Integer item:currentSet   ) {
                EntityRelationshipPaths paths = new EntityRelationshipPaths(graph,val);
                if (paths.hasPathTo(item)){
                    for (RelationshipEdge node:paths.pathTo(item)) {
                        if (node != null){
                            if (!edges.contains(node))
                                edges.add(node);
                        }
                    }
                }

            }

            currentSet.add(val);
        }
        return edges;

    }

    private EntityGraph createGraph(){
        In input = new In("entities_rel_basic.txt");
        EntityGraph graph = new EntityGraph(input);
        return graph;
    }
}
