from functools import cache



def arrangements(springs, checksums) -> int:

    @cache
    def rec(s, g_idx, g_left) -> int:

        if g_idx == len(checksums):
            if "#" in s:
                return 0
            else:
                return 1

        if g_left == -1:
            g_left = checksums[g_idx]

        if s == "":
            if g_idx == len(checksums) - 1 and g_left == 0:
                return 1
            else:
                return 0

        next = s[0]

        if next == '?':
            return rec("." + s[1:], g_idx, g_left) + rec("#" + s[1:], g_idx, g_left)

        if next == '#':
            if g_left == 0:
                return 0
            if g_left > 0:
                return rec(s[1:], g_idx, g_left - 1)

        if next == '.':
            if checksums[g_idx] > g_left > 0:
                return 0
            elif g_left == 0:
                return rec(s[1:], g_idx + 1, -1)
            else:
                return rec(s[1:], g_idx, g_left)

    return rec(springs, 0, checksums[0])


print(sum([arrangements(springs, checksums) for springs, checksums in parse_input("full.txt")]))
print(sum([arrangements(springs, checksums) for springs, checksums in parse_input("full.txt", 5)]))
