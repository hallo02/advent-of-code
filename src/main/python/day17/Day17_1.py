import heapq


# 1302 too high
# Function to calculate the minimum cost using Dijkstra's algorithm
def find_cheapest_path(grid: list[list[int]]) -> int:
    if not grid or not grid[0]:
        return 0

    rows = len(grid)
    cols = len(grid[0])

    # Create a 2D array to store the minimum cost from the start cell to each cell
    costs = [[float('inf')] * cols for _ in range(rows)]
    route = dict()
    costs[0][0] = grid[0][0]

    # Define the directions to explore neighboring cells
    directions = [(0, 1), (1, 0), (0, -1), (-1, 0)]

    # Create a min heap to store the cells with the current minimum cost
    min_heap = [(costs[0][0], 0, 0, {directions[0]: 0,
                                     directions[1]: 0,
                                     directions[2]: 0,
                                     directions[3]: 0}
                 )]

    # Perform Dijkstra's algorithm
    while min_heap:
        cost, row, col, steps = heapq.heappop(min_heap)

        # If the current cost is greater than the minimum cost already known for this cell, skip it
        if cost > costs[row][col]:
            continue

        # Explore the neighboring cells
        for dr, dc in directions:
            new_row = row + dr
            new_col = col + dc

            # If the new cell is out of bounds, skip it
            if new_row < 0 or new_row >= rows or new_col < 0 or new_col >= cols:
                continue

            # Calculate the new cost to reach the new cell
            new_cost = cost + grid[new_row][new_col]

            # If the new cost is lesser than the known cost for this cell, update it
            if new_cost < costs[new_row][new_col]:
                if steps[(dr, dc)] < 3:
                    costs[new_row][new_col] = new_cost
                    next_steps = {directions[0]: 0,
                                  directions[1]: 0,
                                  directions[2]: 0,
                                  directions[3]: 0}
                    next_steps[(dr, dc)] = steps[(dr, dc)] + 1
                    # to -> from
                    route[(new_row, new_col)] = (row, col)

                    heapq.heappush(min_heap, (new_cost, new_row, new_col, next_steps))

    visual(route, rows, cols)
    # Return the minimum cost to reach the destination cell
    return costs[rows - 1][cols - 1]


def visual(route, rows, cols):
    leset = set()
    r = rows - 1
    c = cols - 1
    while (r, c) in route.keys():
        leset.add((r, c))
        r, c = route[(r, c)]
        leset.add((r, c))
    print_it(rows, cols, leset)


def print_it(rows, cols, leset):
    for r in range(rows):
        s = ""
        for c in range(cols):
            if ((r, c) in leset):
                s = s + 'X'
            else:
                s = s + "."
        print(s)


def parse_input(s: str, multiplier=1) -> list[list[int]]:
    def map(line):
        return [int(l) for l in line.strip()]

    with open(s) as input_file:
        return [map(line) for line in input_file.readlines()]


heat_map_2 = [
    [1, 3, 1],
    [1, 5, 1],
    [4, 2, 1]
]

heat_map = [
    [1, 3, 1, 1, 2],
    [1, 5, 1, 1, 2],
    [4, 2, 1, 1, 2]
]

# print(find_cheapest_path(heat_map))
# print(find_cheapest_path(parse_input("full.txt")))
print(find_cheapest_path(parse_input("test.txt")))
