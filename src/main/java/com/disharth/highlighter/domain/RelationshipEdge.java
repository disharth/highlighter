package com.disharth.highlighter.domain;

import com.disharth.highlighter.repository.DataCache;

public class RelationshipEdge {

    private final int to;
    private final int from;
    private final String slashSeperatedIds;
    private final DataCache dataCache;

    public RelationshipEdge(int from , int to, String slashSeperatedIds) {
        this.from = from;
        this.to = to;
        this.slashSeperatedIds = slashSeperatedIds;
        this.dataCache = new DataCache();
    }


    public int getTo() {
        return to;
    }

    public String getSlashSeperatedIds() {
        return slashSeperatedIds;
    }

    public int getFrom() {
        return from;
    }

    @Override
    public boolean equals(Object that) {

        RelationshipEdge thatEdge = (RelationshipEdge)that;
        int thisFirst = Math.min(from , to);
        int thatFirst = Math.min(thatEdge.from , thatEdge.to);

        int thisSecond = Math.max(from , to);
        int thatSecond = Math.max(thatEdge.from , thatEdge.to);
        return (thisFirst == thatFirst && thisSecond == thatSecond);

    }

    @Override
    public String toString() {
        String[] relIds = slashSeperatedIds.split("/");
        StringBuilder stringBuilder = new StringBuilder();
        for (String relId:relIds) {
            Relationship relationship = dataCache.getRelMap().get(Integer.valueOf(relId));
            stringBuilder.append("/("+relationship.getType(from)+")")
                    .append(relationship.getName())
                    .append("("+relationship.getType(to)+")/");

        }
        return ("["+dataCache.getEntityMap().get(from).getName()+"] --- "
                +stringBuilder.toString()
                +"---> ["+dataCache.getEntityMap().get(to).getName()+"]");

    }
}
