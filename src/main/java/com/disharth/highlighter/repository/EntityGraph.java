package com.disharth.highlighter.repository;

import com.disharth.highlighter.domain.RelationshipEdge;
import edu.princeton.cs.algs4.In;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class EntityGraph {

    private static final String NEWLINE = System.getProperty("line.separator");

    private final int V;
    private int E;
    private List<RelationshipEdge>[] adj;


    public EntityGraph(In in) {
        try {
            this.V = in.readInt();
            if (V < 0) throw new IllegalArgumentException("number of vertices in a Graph must be nonnegative");
            adj = (List<RelationshipEdge>[]) new List[V];
            for (int v = 0; v < V; v++) {
                adj[v] = new ArrayList<RelationshipEdge>();
            }
            int E = in.readInt();
            if (E < 0) throw new IllegalArgumentException("number of edges in a Graph must be nonnegative");
            for (int i = 0; i < E; i++) {
                int v = in.readInt();
                int w = in.readInt();
                String relIds = in.readString();
                validateVertex(v);
                validateVertex(w);
                addEdge(v, w , relIds);
            }
        }
        catch (NoSuchElementException e) {
            throw new IllegalArgumentException("invalid input format in Graph constructor", e);
        }
    }

    // throw an IllegalArgumentException unless {@code 0 <= v < V}
    private void validateVertex(int v) {
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
    }

    public void addEdge(int v, int w, String relIds) {
        validateVertex(v);
        validateVertex(w);
        RelationshipEdge relationshipEdgeTo = new RelationshipEdge(v , w,relIds);
        RelationshipEdge relationshipEdgeFrom = new RelationshipEdge(w , v,relIds);
        E++;
        adj[v].add(relationshipEdgeTo);
        adj[w].add(relationshipEdgeFrom);
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(V + " vertices, " + E + " edges " + NEWLINE);
        for (int v = 0; v < V; v++) {
            s.append(v + ": ");
            for (RelationshipEdge w : adj[v]) {
                s.append(w.getTo() + " ("+w.getSlashSeperatedIds()+") ");
            }
            s.append(NEWLINE);
        }
        return s.toString();
    }

    public int V() {
        return V;
    }


    public int E() {
        return E;
    }

    public Iterable<RelationshipEdge> adj(int v) {
        validateVertex(v);
        return adj[v];
    }


}
