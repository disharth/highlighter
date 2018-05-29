package com.disharth.highlighter;

import com.disharth.highlighter.domain.*;
import com.disharth.highlighter.repository.DataCache;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class ResponseWrapper {


    public static RelationshipWrapper wrapAllCategoriesToResponse(List<RelationshipEdge> relationships){
        DataCache cache = new DataCache();
        Map<Integer, Entity> entities = cache.getEntityMap();
        Map<Integer, Relationship> relMap = cache.getRelMap();
        List<RelationshipModel> relationshipModels = new ArrayList<>();
        for (RelationshipEdge edge :relationships) {
            RelationshipModel relationshipModel = new RelationshipModel();

            relationshipModel.setFrom(entities.get(edge.getFrom()));
            relationshipModel.setTo(entities.get(edge.getTo()));
            String[] relIds = edge.getSlashSeperatedIds().split("/");
            List<Relationship> relationshipList = new ArrayList<>();
            for (String relId:relIds) {
                Relationship relationship = relMap.get(Integer.valueOf(relId));
                relationshipList.add(relationship);
            }
            relationshipModel.setRelationships(relationshipList);

            relationshipModels.add(relationshipModel);

        }
        RelationshipWrapper relationshipWrapper = new RelationshipWrapper();
        relationshipWrapper.setRelationshipModels(relationshipModels);

        return relationshipWrapper;

    }
}
