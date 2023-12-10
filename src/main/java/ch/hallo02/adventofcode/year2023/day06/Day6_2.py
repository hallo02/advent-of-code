#!/usr/bin/python3

puzzle = """Time: 60808676
Distance:   601116315591300"""
lines = puzzle.splitlines();
times = [int(t.strip()) for t in lines[0].split(":")[1].split(" ") if t != ""]
distances = [int(t.strip()) for t in lines[1].split(":")[1].split(" ") if t != ""]
print(times)
print(distances)

def isBetter(time, distance):
    better_number = 0
    for x in range(time):
        if((time - x) * x > distance):
            better_number += 1
    return better_number

result = 1
for x in range(len(times)):
    result = result * isBetter(times[x], distances[x])

print("Result",result)
# 35961505


        