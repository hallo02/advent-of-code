def parse_input(s: str, multiplier=1) -> list[tuple[str, list[int]]]:
    def map_line(line: str) -> tuple[str, list[int]]:
        report, group_sizes = line.split()
        return ((report.strip() + '?') * multiplier)[:-1], [int(x) for x in group_sizes.strip().split(',')] * multiplier

    with open(s) as input_file:
        return [map_line(line) for line in input_file.readlines()]


def arrangements(springs, checksums):
    print(springs, checksums)
    def rec(s, g_idx, g_left):

        next = s[0]

        if len(s) == 1:
            if next == '.' and g_idx == len(checksums) - 1 and g_left == 0:
                return 1
            elif next == '#' and g_idx == len(checksums) - 1 and g_left == 1:
                return 1
            elif next == '?' and g_idx == len(checksums) - 1 and g_left <= 1:
                return 1
            else:
                return 0

        # end of group
        if g_left == 0:
            if next == '.' or next == '?':
                return rec(s[2:], g_idx + 1, checksums[g_idx + 1])
            if next == '#':
                return 0

        # keep group idx, refill group left
        if next == '.':
            return rec(s[1:], g_idx, checksums[g_idx])

        # keep group idx, decrease group left
        if next == '#':
            return rec(s[1:], g_idx, g_left - 1)

        if next == '?':
            return rec(s[1:], g_idx, checksums[g_idx]) + rec(s[1:], g_idx, g_left - 1)

    r=  rec(springs, 0, checksums[0])
    print(r)
    return r

print(
    sum([arrangements(springs, checksums) for springs, checksums in parse_input("test.txt")])
)
