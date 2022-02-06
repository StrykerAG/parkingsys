package com.example.parkingsystem.entity;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Navigation {

    private List<Integer> navigation = new ArrayList<>();
    String showDirection(Car car, Parking parking){
        String direction = "";
        for (int i = 0; i < parking.getCrossroadConnections().size(); i++) {
            if (car.getPreviousCrossroadPosition() == parking.getCrossroadConnections().get(i).getCrossroadA() &&
                    car.getCurrentCrossroadPosition() == parking.getCrossroadConnections().get(i).getCrossroadB()){
                direction = parking.getCrossroadConnections().get(i).getDirection();
                i = parking.getCrossroadConnections().size();
            }
        }
        return direction;
    }

    List<Integer> navi(int source, int dest, Parking parking) {
        int v = vCount(parking) + 1;
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < v; i++) {
            adj.add(new ArrayList<Integer>());
        }
        for (int i = 0; i < parking.getCrossroadConnections().size(); i++) {
            addEdge(adj, parking.getCrossroadConnections().get(i).getCrossroadA(), parking.getCrossroadConnections().get(i).getCrossroadB());
        }
        navigation.clear();
        ArrayList<Integer> path = shortestDistance(adj, source, dest, v);
        navigation = path;
        return path;
    }

    private int vCount(Parking parking){
        return parking.getCrossroads().get(0).getMapCrossroads().size();
    }

    private void addEdge(ArrayList<ArrayList<Integer>> adj, int i, int j) {
        adj.get(i).add(j);
        adj.get(j).add(i);
    }


    private ArrayList<Integer> shortestDistance(ArrayList<ArrayList<Integer>> adj, int s, int dest, int v) {
        int pred[] = new int[v];
        int dist[] = new int[v];
        shortestPathAlgorithm(adj, s, dest, v, pred, dist);

        LinkedList<Integer> path = new LinkedList<Integer>();
        int crawl = dest;
        path.add(crawl);
        while (pred[crawl] != -1) {
            path.add(pred[crawl]);
            crawl = pred[crawl];
        }

        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        for (int i = path.size() - 1; i >= 0; i--) {
            arrayList.add(path.get(i));
        }
        return arrayList;
    }

    private boolean shortestPathAlgorithm(ArrayList<ArrayList<Integer>> adj, int src, int dest, int v, int pred[], int dist[]) {
        LinkedList<Integer> queue = new LinkedList<Integer>();
        boolean visited[] = new boolean[v];

        for (int i = 0; i < v; i++) {
            visited[i] = false;
            dist[i] = Integer.MAX_VALUE;
            pred[i] = -1;
        }

        visited[src] = true;
        dist[src] = 0;
        queue.add(src);

        while (!queue.isEmpty()) {
            int u = queue.remove();
            for (int i = 0; i < adj.get(u).size(); i++) {
                if (visited[adj.get(u).get(i)] == false) {
                    visited[adj.get(u).get(i)] = true;
                    dist[adj.get(u).get(i)] = dist[u] + 1;
                    pred[adj.get(u).get(i)] = u;
                    queue.add(adj.get(u).get(i));

                    if (adj.get(u).get(i) == dest)
                        return true;
                }
            }
        }
        return false;
    }
}
