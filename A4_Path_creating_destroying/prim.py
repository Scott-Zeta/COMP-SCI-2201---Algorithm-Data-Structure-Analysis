import heapq

def prim(graph, start):
    mst = []
    visited = set([start])
    edges = [(cost, start, to) for to, cost in graph[start].items()]
    heapq.heapify(edges)
    while edges:
        cost, frm, to = heapq.heappop(edges)
        if to not in visited:
            visited.add(to)
            mst.append((cost, frm, to))

            for next_to, next_cost in graph[to].items():
                if next_to not in visited:
                    heapq.heappush(edges, (next_cost, to, next_to))

    return mst

# Example graph
graph = {
    'A': {'B': 4, 'D': 6},
    'B': {'A': 4, 'C': 5, 'D': 3},
    'C': {'B': 5, 'D': 8, 'E': 2},
    'D': {'A': 6, 'B': 3, 'C': 8, 'E': 7},
    'E': {'C': 2, 'D': 7}
}

print("Minimum Spanning Tree (Prim):", prim(graph, 'A'))
