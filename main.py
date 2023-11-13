import sys
import heapq

def string_to_int(s):
    if "0" <= s <= "9":
        return int(s)
    elif 'A' <= s <= 'Z':
        return ord(s) - ord('A')
    elif 'a' <= s <= 'z':
        return ord(s) - ord('a') + 26
    
def int_to_string(i):
    if 0 <= i <= 25:
        return chr(i + ord('A'))

def parse_input():
    if len(sys.argv) != 4:
        print("Require 3 arguments")
        exit(0)
    
    information = []    
    for argv in sys.argv[1:]:
        table = argv.split(",")
        table_data = []
        for row in table:
            row_data = []
            for column in row:
                row_data.append(string_to_int(column))
            table_data.append(row_data)
        information.append(table_data)
    return information
    
def combine_table(connection, construction, destruction):
    graph = {}
    for i in range(len(connection)):
        row = {}
        for j in range(len(connection[i])):
            if connection[i][j] == 0:
                row[int_to_string(j)] = construction[i][j]
            else:
                row[int_to_string(j)] = - destruction[i][j]
        graph[int_to_string(i)] = row  
    return graph

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

def main():
    information = parse_input()
    connection = information[0]
    construction = information[1]
    destruction = information[2]
    print(f"Connection: {connection}")
    print(f"Construction: {construction}")
    print(f"Destruction: {destruction}")
    graph = combine_table(connection, construction, destruction)
    print(f"Graph: {graph}")
    
    mst = prim(graph, 'A')
    print("Minimum Spanning Tree (Prim):", mst)
    existRoadCost = 0
    for i in range(len(connection)):
        for j in range(len(connection[i])):
            if connection[i][j] == 1:
                existRoadCost += destruction[i][j]
    existRoadCost //= 2
    print(f"Exist road cost: {existRoadCost}")
    mstCost = 0
    for e in mst:               
        mstCost += e[0]
    print(f"Minimum Spanning Tree cost: {mstCost}")
    
    FinalCost = existRoadCost + mstCost
    print(f"Final cost: {FinalCost}")
    
if __name__ == "__main__":
    main()
    
## input:
# all connection: python3 main.py 1111,1111,1111,1111 ABFE,BACG,FCAD,EGDA ABFE,BACG,FCAD,EGDA
# mixed case: python3 main.py 011000,101000,110000,000011,000101,000110 ABDFFF,BACFFF,DCAFFF,FFFABD,FFFBAC,FFFDCA ABDFFF,BACFFF,DCAFFF,FFFABD,FFFBAC,FFFDCA
# no connection: python3 main.py 000,000,000 ABD,BAC,DCA ABD,BAC,DCA
# suit case: python3 main.py 0001,0001,0001,1110 AfOj,fAcC,OcAP,jCPA AWFH,WAxU,FxAV,HUVA