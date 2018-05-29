package com.disharth.highlighter.repository;

import com.disharth.highlighter.domain.RelationshipEdge;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;

public class EntityRelationshipPaths {


    private static final int INFINITY = Integer.MAX_VALUE;
    private boolean[] marked;  // marked[v] = is there an s-v path
    private RelationshipEdge[] edgeTo;      // edgeTo[v] = previous edge on shortest s-v path
    private int[] distTo;      // distTo[v] = number of edges shortest s-v path


    public EntityRelationshipPaths(EntityGraph G, Iterable<Integer> sources) {
        marked = new boolean[G.V()];
        distTo = new int[G.V()];
        edgeTo = new RelationshipEdge[G.V()];
        for (int v = 0; v < G.V(); v++)
            distTo[v] = INFINITY;
        validateVertices(sources);
        bfs(G, sources);
    }

    public EntityRelationshipPaths(EntityGraph G, int s) {
        marked = new boolean[G.V()];
        distTo = new int[G.V()];
        edgeTo = new RelationshipEdge[G.V()];
        validateVertex(s);
        bfs(G, s);

    }

    // breadth-first search from a single source
    private void bfs(EntityGraph G, int s) {
        Queue<RelationshipEdge> q = new Queue<RelationshipEdge>();
        for (int v = 0; v < G.V(); v++)
            distTo[v] = INFINITY;
        distTo[s] = 0;
        marked[s] = true;
        q.enqueue(new RelationshipEdge(-1 , s,"-1"));

        while (!q.isEmpty()) {
            RelationshipEdge v = q.dequeue();
            for (RelationshipEdge relationshipEdge : G.adj(v.getTo())) {
                int w = relationshipEdge.getTo();
                if (!marked[w]) {
                    RelationshipEdge newRel = new RelationshipEdge(v.getFrom(),v.getTo() , relationshipEdge.getSlashSeperatedIds());
                    edgeTo[w] = relationshipEdge;
                    distTo[w] = distTo[v.getTo()] + 1;
                    marked[w] = true;
                    q.enqueue(relationshipEdge);
                }
            }
        }
    }

    // breadth-first search from multiple sources
    private void bfs(EntityGraph G, Iterable<Integer> sources) {
        Queue<Integer> q = new Queue<Integer>();
        for (int s : sources) {
            marked[s] = true;
            distTo[s] = 0;
            q.enqueue(s);
        }
        while (!q.isEmpty()) {
            int v = q.dequeue();
            for (RelationshipEdge relationshipEdge : G.adj(v)) {
                int w = relationshipEdge.getTo();
                if (!marked[w]) {
                    edgeTo[w] = relationshipEdge;
                    distTo[w] = distTo[v] + 1;
                    marked[w] = true;
                    q.enqueue(w);
                }
            }
        }
    }

    /**
     * Is there a path between the source vertex {@code s} (or sources) and vertex {@code v}?
     * @param v the vertex
     * @return {@code true} if there is a path, and {@code false} otherwise
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public boolean hasPathTo(int v) {
        validateVertex(v);
        return marked[v];
    }

    /**
     * Returns the number of edges in a shortest path between the source vertex {@code s}
     * (or sources) and vertex {@code v}?
     * @param v the vertex
     * @return the number of edges in a shortest path
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public int distTo(int v) {
        validateVertex(v);
        return distTo[v];
    }

    /**
     * Returns a shortest path between the source vertex {@code s} (or sources)
     * and {@code v}, or {@code null} if no such path.
     * @param  v the vertex
     * @return the sequence of vertices on a shortest path, as an Iterable
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public Iterable<RelationshipEdge> pathTo(int v) {
        validateVertex(v);
        if (!hasPathTo(v)) return null;
        Stack<RelationshipEdge> path = new Stack<RelationshipEdge>();
        int x;
        for (x = v; distTo[x] != 0; ) {
            path.push(edgeTo[x]);
            if (x == edgeTo[x].getTo())
                x=edgeTo[x].getFrom();
            else
                x = edgeTo[x].getTo();
        }
        path.push(edgeTo[x]);
        return path;
    }

    // throw an IllegalArgumentException unless {@code 0 <= v < V}
    private void validateVertex(int v) {
        int V = marked.length;
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
    }

    // throw an IllegalArgumentException unless {@code 0 <= v < V}
    private void validateVertices(Iterable<Integer> vertices) {
        if (vertices == null) {
            throw new IllegalArgumentException("argument is null");
        }
        int V = marked.length;
        for (int v : vertices) {
            if (v < 0 || v >= V) {
                throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
            }
        }
    }


}
