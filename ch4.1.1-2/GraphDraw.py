import networkx as nx
import matplotlib.pyplot as plt
import numpy as np

G = nx.Graph()

with open('GraphCache.txt') as f:
    lines = f.readlines()
    for line in lines:
        a=line.strip().split(", ")[0]
        b=line.strip().split(", ")[1]
        G.add_edge(a, b)

# explicitly set positions
# pos = {1: (0, 0), 2: (-1, 0.3), 3: (2, 0.17), 4: (4, 0.255), 5: (5, 0.03)}

options = {
    "font_size": 16,
    "node_size": 500,
    "node_color": "white",
    "edgecolors": "black",
    "linewidths": 2,
    "width": 2,
}


fig, ax = plt.subplots(figsize=(14, 8))
pos = nx.spring_layout(G, seed=3068)
nx.draw_networkx(G, pos, **options)

# nx.draw_networkx(G, **options)

# Set margins for the axes so that nodes aren't clipped
ax.margins(0.01)
plt.axis("off")
plt.show()
