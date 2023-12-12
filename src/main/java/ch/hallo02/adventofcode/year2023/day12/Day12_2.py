#!/usr/bin/python3

# Solution by Robin Schoch

import re
from functools import cache


def parse_input(s: str, multiplier=1) -> list[tuple[str, list[int]]]:
    def map_line(line: str) -> tuple[str, list[int]]:
        report, group_sizes = line.split()
        return ((report.strip() + '?') * multiplier)[:-1], [int(x) for x in group_sizes.strip().split(',')] * multiplier

    with open(s) as input_file:
        return [map_line(line) for line in input_file.readlines()]


test_input = './test.txt'

full_input = './full.txt'


def part_1_and_2(s: str, mutliplier=1) -> int:#
    def calculate_arrangement(records: tuple[str, list[int]]) -> int:
        s, nums = records
        s = re.sub(r'(\.+)', '-', s.strip('.')) + '-'  # .split(' - ')

        def consume(x: str, n: int) -> (str, bool):
            if len(x) + 1 < n:
                return "", False
            for i in range(n):
                if x[i] == '-':
                    return "", False
            if x[n] == '#':
                return '', False
            return x[n + 1:], True

        @cache
        def dp(x: str, ns: int) -> int:
            if len(x) == 0 and ns == len(nums):
                return 1
            elif len(x) == 0 or ns == len(nums) and x[0] == '#':
                return 0
            elif x[0] == '-' or ns == len(nums) and x[0] == '?':
                return dp(x[1:], ns)
            elif x[0] == '#':
                next_x, legal = consume(x, nums[ns])
                if not legal:
                    return 0
                return dp(next_x, ns + 1)
            elif x[0] == '?':
                next_x, legal = consume(x, nums[ns])
                if legal:
                    return dp(x[1:], ns) + dp(next_x, ns + 1)
                return dp(x[1:], ns)
            return 0

        x = dp(s, 0)
        return x

    return sum([calculate_arrangement(records) for records in parse_input(s, mutliplier)])


print(part_1_and_2(test_input))
print(part_1_and_2(full_input))
print(part_1_and_2(test_input, mutliplier=5))
print(part_1_and_2(full_input, mutliplier=5))