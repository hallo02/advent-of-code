package ch.hallo02.adventofcode.year2023.day4;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

public class Day4_1 {
    public static void main(String... args) {
        var result = puzzle.lines()
                .map(l -> mapToLists(l))
                .mapToInt(m -> toPoints(m))
                .sum();

        System.out.println(result);


    }

    static int toPoints(Map<String, List<Integer>> m) {
        m.get("winningNumbers").retainAll(m.get("mineNumbers"));
        var points =  m.get("winningNumbers").size() == 0
                ? 0
                : (int) Math.pow(2, m.get("winningNumbers").size() - 1);
        System.out.println(points);
        return points;
    }

    static Map<String, List<Integer>> mapToLists(String line) {
        String winning = line.split("\\|")[0].split(":")[1];
        var winningNumbers = Arrays.stream(winning.split(" "))
                .map(String::trim)
                .filter(n -> !n.isBlank())
                .mapToInt(n -> Integer.valueOf(n))
                .boxed()
                .toList();

        String mine = line.split("\\|")[1];
        var mineNumbers = Arrays.stream(mine.split(" "))
                .map(String::trim)
                .filter(n -> !n.isBlank())
                .mapToInt(n -> Integer.valueOf(n))
                .boxed()
                .toList();

        HashMap<String, List<Integer>> result = new HashMap<>(
                Map.of("winningNumbers", new LinkedList<Integer>(winningNumbers),
                        "mineNumbers", new LinkedList<Integer>(mineNumbers))
        );
        return result;

    }

    static String puzzle = """
            Card   1: 13  4 61 82 80 41 31 53 50  2 | 38 89 26 79 94 50  2 74 31 92 80 41 13 97 61 82 68 45 64 39  4 53 90 84 54
            Card   2: 25 44 86 77 98 91 55 39 63 12 | 84 62 55 28 99 26 19 18 13 57 97 63 20 65 24 31 72 41 77 27 50 30 38  3 88
            Card   3: 96 46 60 19 82 25 41 29 38 94 | 43 82 86 74 16 15 92 46 32  3 17 30 42 98 60 12 96 38 19 35  6 29 72 25 62
            Card   4: 19 55 20 50 57 36 61 35 59 75 | 75 11 94 28 49 59 38 32 55 35 61 68 29 36 97 57 50 20 70 52 13 53 87 19 42
            Card   5: 63  6 84 48 45 55 43 86 33 60 | 68 99 84 24 23 39 48 87 60  1 12 43 21 85 54 88 36 67 81 77 13 96  6 86 55
            Card   6: 57 24 81 65 12 13 58 30 33  4 | 78 96 50 92  3 47 22 99 28 31 46 98 93 77 49 68 90 25 51 21 16 83 26 23 36
            Card   7: 68 80 65 13 23 61 28 77 42 47 | 76 93 52 13 61 65 21 14 68 81 79 47 11 28 77 92 40 80 69 89 55 32 23 85 42
            Card   8: 19 24 80 12 94 32 53 20 57 72 | 30  2 79 19 95 89 29  4 35 87 15 78 13 33 51 17 21 53 68 90 67 72 26 40 28
            Card   9: 28 30  5 92 48  7 54 39 90 31 | 10 93  6 42 41 66 40 90 56 54 48 47 87 27 59  7 30 39  5 99 92 85  3 28 35
            Card  10: 84 75 46 44 99 60 14 47 56  3 | 19 99 47 41 84 12 82 45 98 40 43 92 60 75 44 76  3 17 54 46 14 56 61 63 95
            Card  11: 45 52 83 35 91 66 11  9 38 71 | 45 21 22 29 18 77 41 38 53 11 48 57 70 73 79 85 13 39 99 81 40 15 51 60 14
            Card  12: 23 48  9  8 76 37 70 68 44 73 | 84 19 73 27 71 85 41 32  6 33 24 51 88 56 87 28 14 15 50 55 21 35 66 68 60
            Card  13: 32 44 71  5 45 72  6 16 78 90 | 38 90 13 44 71 62 45 22 16 72 33  6 85 12 67  1 80 66 56 32 53 10  5 78 68
            Card  14:  6 73 88 83 56 96 75 37 25 18 | 82 15 83 58 96  3 12 74 20 60 38 46 47 31 65 51 85 55  7 87  6 73 86 34  8
            Card  15: 43 69 37 16 50 19 24 54 78 18 | 41 46 39 31 75 90 67 84 56 86  1 27 93 96 36 81 98 48 80 44 23 66 59 13 63
            Card  16: 58  5 14 42 87 52 32 20 69 73 | 69 14 79 42 73 58  5  4 87 36 59 32 22 15 31 46 68 47  8 78 10 92 20 37 40
            Card  17: 37  8 85 57  7 93  4 29 31 28 | 55 20  4 29  8 40 57 37  2 60 31 46 68 66 88 14 93 17 78 10  9 59 95 39 70
            Card  18: 58 13 65  3 85 71 80  2 40  6 | 92 27 85  1 81 12 94 62 76 33 87 96 54 24 89 16 95 21 22 80 43 25 98 97 73
            Card  19: 87 82 44 17  8 29 18 84  6 39 | 97 55  6 14 69 12 32  5  2 59 44 18 93 41 88 74 25 68 81 85 65 23 71 28 86
            Card  20: 13 80 52 31 70  3 81 17 87 88 | 71 65 94 88 26 70 14 78 24  5 77 25 34 56 86 20 99  6 91 52 36  1 30 33 73
            Card  21: 50 57  7 98 29 74 94 24 89 42 | 95 75 77 37  5 93 73 42 33 68 88 39 66 71 76 20 44  6 89 26 83 84 45 23 54
            Card  22: 55 24 53 52 11 85 68 39 38 59 | 99 62 52 66 25 78 41 81 88 20 84 43 36 31 79 40 18 48  2 74 37 23  3 11 80
            Card  23: 87 30 66 28 86 94 35 46 58 57 | 61 93  4 74 60 32 37 73  2 11 20 43 40 58 96 90 91 26 63 76 23 92 34 89 38
            Card  24: 50 54 93  5 47 88 26 87  2 80 | 29 48 34 55 40 25 30 76 38 86 85  4  6 27  7 70 19 95 61 84 16  8 90 28 63
            Card  25:  5 59 16 46 58  9 35 11 84 75 | 26 33 39 31 28 99 20 25 50 48 86 70 89 51 45 30  4 80 66 53 10 38 83 87 36
            Card  26: 13 65 90 19  3 69 82 23 73 25 | 75 64 31 72 53 19 85 27 97 86  2 56 20 34 98 82 76 88 73 63  9 40 74 83 39
            Card  27: 32 27 31 81 63 99 29 41 23 57 | 29 99 63 36 66 38 45 49 94 80 46 40 96 60 67 22 51 55 23 81 27 31 32 12 39
            Card  28: 65 22 81 46 98 68 41 87 17 59 | 10 70 57 46 39 91 76 56 97 71 68 61 98 59  4 79 74 29 81 22 65 94 19 78 12
            Card  29: 41 21 30 65 69  2 92 72 48 31 | 35 48 31 61 65  3 23 41 92 69 50 34 29 38 80  9  5 87 24  2 55 67 10 21 37
            Card  30: 22 56 60 61 10 38 53 98 77  1 | 31 77 60 26 18  1 56 11  9 24 83 95  8 66 97 70 91 10 46 12 27 55 98 47 81
            Card  31: 21 61 42 15 24 58 38 25 22 41 |  2 67 42 48 20 81 47 46 44 26 40 24 32 84 85 87 59  6  1 49 57 35 55 23 98
            Card  32: 65 71 57  6 49 33 52 70 93 46 | 25 93 71 70 86 94 57 81 92 49 20 15  7 33 47 99 76 48 88 41 43  3 69 82 54
            Card  33: 20 65 52 73  6 62 95 60  4 17 | 26 30 95 40 97 20 59 17 50  4 27 79 65 75 52 60 93 83 42  5 89 34 56 92 81
            Card  34: 89 29 70 26 60 85  4 38 56 13 | 25 65 75 95 54 60 31 69 44 82 63 90 14 32 72 76 35 30 70  2 34 10 81 33 27
            Card  35:  4 48 24 94 46 35 51 45 27  7 |  9 59 67  4 83 18 12 94 73 78 25 90 42 11 68 48 57 86 41 37 49 45 22 38 76
            Card  36: 77 78 55 88 65 59 64 94 24 92 | 84 39 94 22 23 56 28 13 10 37 34 64 93 79 44 54  2  6 63 19 47 29 90 73 70
            Card  37: 24 29 74 31 19 91 86 64 53 56 | 83 14 25 65 76 88 57 49 89 99 58 55 67 63 54  5  4 52 29 51 79 78 90 32 86
            Card  38: 66 47 22 15 92 99 87 78 81 57 | 17  8 56 24 36 42 27 45 87 30 41 37 61 51 92 79 75 59 94 31 77 60 66 25 55
            Card  39: 94 29 60 84 24 26 11 83 88 91 | 67 75 30 62 89 20  1 25 72 93  5 65 77 66 39 85 52 86 98 35 44 22 97 55 38
            Card  40: 88  4 12 46 59 10 48 55 14 91 | 18 60 78 71 96 49 72 52 77 29 25  6 27 68 45  2 40 47 28 89 95 80  9  8 65
            Card  41: 39 15 13 52 75 78 28 55 81 42 | 72 34 48 95 23 29 21 96 64 45  8 88 83 91 25 73 18 31 37 46 65  1 98  9 79
            Card  42: 69 58  4 83 35 43 68  8 62 21 | 20 70  5 21 43 68 10  8 35 22 92 52 58 64 62 27 83 99  6 26 97  1 14  4 69
            Card  43: 78 38 80 41  7 42 10 37 70 75 | 90 22 54 58 47 95 65 85 10 79 69 21 77 30  8 40 49 92 44 15 55 25 14 29 23
            Card  44: 91 46 36 90 83 64 12 53 84 61 | 83 12 94 80 84 87 53 49  1 48 20 23 36 91 54 64  3 15 79 75 90 46 92 61 73
            Card  45: 94 46 78 84 66 22 56 59 42  5 | 11 47 66 42 91 45 55 56 80 31 68 38 43 54 41 46 90 22  5 52 78 59  2  6 21
            Card  46:  5 10  3 68 14 54 87 96 60 43 | 18 46 87 96 54  3 82 95  5 55 62 72 45 60 68 70 14 10 40 27 12 43 94  6 52
            Card  47: 36 55 58 23  9  6 67 65 47 69 | 90  6 37 49 26  9 73 60 28 31 92 22 64 58 23 52 70 13 61  2 67 25 53 36 19
            Card  48: 61 15  2  7  5 32 77 99 29 83 | 24 40  3  2 29 77 11  7 68 69 58 80 78 83 61 32 99 44 64 34 15 54  5 48 53
            Card  49:  1 57 77 56  7 67 60 43 82 10 | 43 91 60 76 56 67 53 77  4 74 10  7 84 46 62  1 28 96 97 58 57 33 82 36 70
            Card  50: 29 15  1 95 57 21 45 88 38 53 | 26 66 46 52 93 59 57 60 72 91 82 21  9 53 64 19 81 24 38  7 58 15  1 90 23
            Card  51: 49 56 86 75 20 55 28 72 46 10 | 49 37 50 20 55 72 39 67 59 81 22 73 64 45 35 56 46 63 28 86 92 33 10 90 12
            Card  52: 58 96 57 92 43 17  8 34 39 53 | 71 98  7 49 69 62 91 55 29 73 21 12 60 10 13 70 95 20 36 86 92  8 25 53 54
            Card  53:  5 32 81 43  4 70 69 85  1 99 |  3 37 29 41  8 12 35  9 21 13 49 42  4 97 82  6 51 92 33 17 27 66 20 54 39
            Card  54: 69 46 76 98 43 17 53 91 37 95 | 88 50 43 79 46 91 73 53 64 37 76 67  7 18 96 55 69 93 17 14 95 60 42 77 98
            Card  55: 92 97 75 76 18 96 67 72 83 23 | 51 69  2 58 14 12 30 90 72 93 64  4 34  8 83 18 92 29 43 76 62 98 46 55 75
            Card  56: 94 36 54 37 30 22 68 26 43  6 | 55 58 91 11 60 46 61 74 80 68 36 37 43 82 64 10 30 96  6 38 29 28  1 94 54
            Card  57: 64 74 98 21 62 15 20 52 95 53 | 37 44 59 95  5 79 42 22 93 89 98 50 83 66 87 13 27 19 24 38 52 33 63 70 48
            Card  58: 38 27 93 67 83 37 12 33 95 45 |  7 13 95 56 81 23 24  9 41 96 36 98 83  8 93 80 37 28 72 20 32  5 55 90 52
            Card  59: 46 63  3  8 51 40 97 37 53 15 | 14 97 99 16 15 67 51 33 37 56  3 44 81 30 96 74  8 28 61 21 63 60 84 89  9
            Card  60: 94 75 90 93 59  8 77 41 86 76 | 26 32 95 81  8 49 89 17 75 70 50 96 25 33 41 72 27 24 97 35 12  3 45 76 36
            Card  61: 98 39 38 52 92 81 34 31 68 16 | 52 58 69 85 28 42  2 26 93 22  9 64 53  5 97 15 25 31 65 40 51 43  3 84 70
            Card  62: 21  5 65 58 68 61 98 18 77 75 | 81 44 54 45 90 47 66 72 87  3 16 51 14 82 73  4 83 84 58 67 53 28 15 20  1
            Card  63: 61 55 41 78 40 24 98 52 31 93 | 36 33 17 66 22 26 28 63 10  4 80 69 56 19 12 59 94 18 30 95 16 37 54 79  2
            Card  64: 16 12 80  3 36 59 53 22 28 37 |  1 67  9  5 82 55 36 97 90 28 70 43 38 69 45 88 29 76 35 31 73 25 94 30 78
            Card  65: 41  2 52 88 21 90  7 26 47 46 |  6 57 12 68 46 94 43 76 28 67 24 45 22 98 71 79  4 42 49 30 85 39 38 54 11
            Card  66: 55 37 90 38 28 23  8 99 91 13 | 51 97 46 85  2 42 44 75 50 86 94 52 89 18 33 98 83 17 27 16 78 84 57 56 43
            Card  67: 68 86  5 19 27 65  1  7 11 35 | 83 48 22 29 34  7  9  5 84 44  2 35 91 15 42 24 86 64 31 32 47 89 51  4 63
            Card  68: 38 92 73 53 12 34 55 35 29 33 | 34 90 92 38 12  3 50 73 69 96 60 49 70 53 76 33 97 55 29  2  7 23 57 35 58
            Card  69: 91 49 95 35 78 37 51 90 30 19 | 59 27 37 90 29 91 45 47 49 19 95 63 35 55 70 17 44  1 56  5 78 30 51 46 80
            Card  70: 52 10 91 28 92 30 78 14 57 27 | 78 35 83 52 69 14 74 16 63 45 33  1 10 92 91  6 58 28 60 27 57 31 99 17 30
            Card  71: 30 43 56 98 37 42 65  4 66 58 | 90 26 67  3 89 25 68 52 80  2  6 15 99  4 55 23 45 22 66 95 88  1 69 28 76
            Card  72: 35 98 82 62 87 14 70 54 81 73 | 73 29 82 56 79  1 85 98 33 12  9 25 46 72 16 45 11 95 78 28 38 57 76 62 71
            Card  73: 98 71 45 28 21 81  7 84  3 95 | 69 37 28  3 45  4 46 79 61 63 13 56 23 81 94 60  8 22 59 85 12 11 93 78  6
            Card  74: 87 42 74 79 39 44 26 27  1 80 | 73  7 88 64 29 47  8 72 31 74 32 14 87 80 85 12  6 38 86 45 26 79 96 59 67
            Card  75: 49 12 76 36 85 84 42 62 83  1 | 88 76 42 85 72 49 83  9 29 64 89  4 39 79 97 60 22 48  5 96 36 46 59 68 52
            Card  76: 91  6 83 56  9 42 72 88 28 66 | 96 39  5 88 17 97 76 82 35 84 86 16 63 44 57 37 91 31 13 42 47 14 92 10 40
            Card  77: 46 99  4 86 91 24 23 98 48 44 | 13  9 55 34 69 40 22  5 30 88 72 21 47 45 74 66 10 43 76 80 41 89 24 56 42
            Card  78: 82 78 29 97 61 72 83 42 92 68 | 46 99 51 35 39 67 43 34 40 12 83 61 92 25  1 56  5 64 24 42 41 68 32 62 28
            Card  79: 13  4  1 85 12 72 73 37 80 14 | 39 93 89 81 22 19 37 78 42 35 23 86 24 68 20 66 74  8 67 50 29 26 27 25  7
            Card  80: 74 22 79 70 32  2 14  1 23 57 | 72 11 81 79 74 68 80 53 60 95  8 43  6 67 41 37 29 86 82 62 76 92 18 56 55
            Card  81: 28 25 23 52 82 11 70 87 27 83 | 75 55 48 95  2 10 46 41 39 42 11 94 99 69 20 31 34 33 97 28 15 63 53 59 68
            Card  82:  8 53 42 39  5  9 78 16 38 65 | 18 34 12 47 54 84 58  4 70 77 21 31 32 59 43 57 20 73 13  1 74 63 68  7 15
            Card  83:  5 87 50 62 14 20 23 76 95 48 | 74 25 78 81 93 45 55 96 67 28 32 39 49  4 92 51 53  1 15 30 35 82 68 11 61
            Card  84: 26  2 46 74 56 47 64 27 69 44 | 42 44 74  6 93 45 86 89 27 43 47 73 56 69 64 46 58 20 92  2 26 32 76 63 31
            Card  85: 61  7 33 50 76  4 67 32  9 88 | 79 38 61  8 29 18 15 76 95 54 56 88  6 62 26 28 11 78 52  7 57 74 41 63 87
            Card  86: 86 17 74 18 62 51 89 82 71 37 | 59 12 63 44 78 45 81 13 29 93 62 86 58 54 31 96  8 75 92 27 99 56 35 33 50
            Card  87:  8 89 48  9 35 88 82  2 58 29 | 88 29 32  8  2 40  6 48 31 19 91 97 58 21 42 82 73 61 28 89 35 94 74  9  7
            Card  88: 28 51 58  6 88 15 84 21 92 19 |  6 88 32 62 21 91 55 51 19 28 45 58 98 43 26 27 41 71 84 92 14 47 96 85 15
            Card  89: 78 48 94 62 54 43 70 13 44 75 | 70 83 71 89 26 96 42 46 21 99 30 40 48 57 49 85 82 62 79 80 98  1  9 45 22
            Card  90: 84 77 94 82 87 78 31 74 20 26 | 40 90 76 13 96 79 94 98 43 78 88 72  2 47 14  3  5 64 61 59 29 33 52 23 85
            Card  91:  7 82 12  9 43 90 85 48 81 30 | 86 48 12 85 26 64 82 43 44 58 41 90  7 47 36  6 63 29  9 42 81 28 23 83 30
            Card  92: 96 50 11 22 34 61 28 26 59 20 | 34 57 50 27 59 30 21 91 11 13  6 73  9 39  8 19 22  3 28 80 61 20 26 96 51
            Card  93: 51 38 61 68 57 66 30 32 98 56 |  3 31 61 26 86 20 22 47 81 74 41 58 24 46  4 59 13 35 87 64 15 40 48 73  9
            Card  94: 59 39 16 10 74 37 58 77 79 71 | 18 71 76 34 82 85  7  5 60 41 91 39  6 32 81 28 77 55 14 42 37 79 24 35 56
            Card  95: 17 29 59 47 75 53 37 67 69 96 | 20 16 60 84 23 65  5 97 92 13 99 15  2 37 87 18  4 66 35 64 81 55 61  8 54
            Card  96: 19 95 57  3 65 82 78 10 84 31 | 61 47 57 36 32 17  7 99 46 51 65  1 18 10 62 73 23 38 70 69 39 41 81  6 63
            Card  97:  2 83 18 49 60 86 42 37 12 39 | 28 26 74 51 79 52 90 77  2 34 64  4 53 42 50 82 68 38 37 18  7 40 60 30 16
            Card  98: 38 77 44 12 78 79 33 74 64 50 | 77 70 80 49 33 74  7  3  6 81 69 88 78 71 22  9 53 79 27 25 14 39 37 50 34
            Card  99: 93 11 71 39 88 25 96 66 84 40 |  8 39 63 10 97 71 27 60 82  1 12 46 89 43 99 37  7  2 14 53 62 80 35 33 69
            Card 100: 41 59 31 21 80 40 89 44 73 94 | 70 83 90 54 66 44  5 46 32 34 77 10 12  9 38 85 18 15 94  6 89 82 21 33 17
            Card 101: 83 79 29 23 95 59 60 40 35 48 |  1 79 33 32 71 75 64 43 87 73 51  8 78 28 10 99 55 19 46 38 93 24 34 37 48
            Card 102: 81 36 92 91 29 18 59 74 13 52 | 20 44 17  2 99 53 35 30 19 47 38 48  5 93 66 45  7 70 63 64  6 28 25  1 16
            Card 103: 31 18 73 49 93 29 94 71 41 85 | 66 10 79 88 82  2 65 92 43 26 52  6  8 72 53 58 97 30 32 90 68 13 76 55 23
            Card 104: 20 72 59 79 93 48 83 99 82 54 | 22 25 30 91 95 29 39 87 37 62 34 86 69 77  3 31 35 63 28 23 64 47 55 33  6
            Card 105: 78 24 67 34 72 95 33 98 55 53 | 99 74 40 62 34 96 24 18 46 49 73 92 35 22 91 70 12 47 95 60 23 30 31 13 59
            Card 106: 92 89 86 37 20 79 39 29 77 66 | 41 92 99  5 83 85 56 96 60 27 46  2 79 76 59 74 53 19 84 10 93  1 94 80 88
            Card 107: 23 70  4 48 76 51 78 67 85 37 | 21 63  4 98 13 37 41 55 35 85 62 70  1 76 67 45 96 78  6 29 11 15 48 51 23
            Card 108: 23 61 34 97  9 49 95 67 11 24 | 49 88  9 67 23 44 90 68 47 11 61 71 72  6 34 95 55  2 82 41 26 28 97 58 24
            Card 109: 62 99 23 57 91 67 34 79 68 20 | 79 91 33 58 99 93 14  2 55 13 16 88 39 41  4 25 56 48 67 78 34  9 29  5 84
            Card 110: 99 39 37 34 73  7 72 76 31 92 | 31 81 18 37 68 27  7 47 72 29 96 82 30 16 10 42 73 12 39 76 51 92 88 93 99
            Card 111: 10 83 57 65 27 41 23 31  4 92 | 78 91 97  6 90  4 71 55 74 31 48 80 65 35 76 23 27 89  2 13 21 49 57 67 22
            Card 112: 38 41 85 98 32 71  3 88 19 31 | 91 26 50  2 48 18 75 51 40 71 39 24 29 21 88  9 60 37 83 85 55 65 74 96  5
            Card 113: 79 92 17 23 54 62 41 55  8 25 | 72 41 51 16 60 32 62 17 30 23 22 54 18  7 97 92 25 78  8 94 79 52 88 55 77
            Card 114: 59 73 75 95 87 77 82 56 36 64 | 37 13 52 32 64 36 77 75 57 33 99 19 44 42 82 14 69 59 56 34 48 95 87 30 73
            Card 115: 42 63 46 64 31 94 49 95 16 30 | 95 23 31 55 94 78 40 49 64 46 61 16 26 63 57 19 29 76 34 27 47  2 42  5 30
            Card 116:  1 77 39 92 91 38 50 14  2 17 | 16 69 62 99 67 83 59  2 88 70 89 10 91 54 63 48 78 29 79 73 34 39 61 60 28
            Card 117: 62 50  4 87 91 90  3 69  2 97 | 89 20 38 55 74 48 69 28 73 60  3 45 40 46 50 57 22 64 93 14  2 62 90 19 87
            Card 118: 94  7 17 87 42 23 19 70 47 15 |  9 82 17 34 40 72  2 87 54  4 42 29 39 86 90 77 23 70 48  7 89 16 49 69 98
            Card 119: 43 92 55  6  4 20 23 74  3 63 | 93 82  6 44 27 92 22 72 66 23 24 39  2 76 52 61 55 26 10 53 75 58 31 42 64
            Card 120: 33 67 88 80 77 21 38  8 17  4 |  8 73 25 14 57 12 59 74 84 75 50 88  1  4 38 19 43 39 96 33  9 63 10 90  5
            Card 121:  3 60 65 46 62 72 50 92 43 98 | 66 12 43 27  8 22 99 62 78 10 75 73 91 48 42 92 30 51 94 54 45 81 80 74 19
            Card 122: 18 15 74 48 62 81 82 38 29 41 | 20 76 61 73 49 47 14 37 77 24 63 10  3 88 78 53 25 56 39 54 71 83 86 60 21
            Card 123: 30 66 51 36 79 52  6 28 44 24 | 77 95 79 36 74 67  7 20 50 96  1 21 80 57 16 44 19 48 82 17 56 94 32 35 85
            Card 124: 38 58 62 16 41 93 71 72 23 63 | 92  2 74 51 32 50 48 41 12 89 23 58 98 77 33 66 79 27 20 55 90 25 29 39 42
            Card 125: 28 37  9 78 63 11 89 98 94 75 |  4 35 20 67 87 64 12 13  3 25 77 33 31 68 82 21 17 27 19 41  1 54 36 59 65
            Card 126: 67  3 60  8 75 28 95 41 48 93 | 16 36 80 97 26 32 96 21 81 58 31 82 99 71 92 78 68  9 65 84 73 51 79 12 22
            Card 127: 25 20 21  6  5 41 94 93 70 85 | 32 39 38 35 23 26 34 22 49 37  4 29 15 86 59 89 99 31 65 42 24 96 60 79 90
            Card 128:  8 41 61 19 59 56 79 12 85  4 | 21 22  7 68 85 60 48 32 74 12 24 59 79 54 61 19 56  8 14  4 23 84  1 41 70
            Card 129: 74 75 27 60 87  3 31  7 98 41 |  2 27 75 98 53  7 60 21 42 65 49 71  3 85 41 55 87 31  5 34 90 88 64 99 74
            Card 130: 48 62 20 71 95 36 85 55  2 31 | 95  9 23 39 54 43 79 81 36 94 20 71 75 16 62  4 84  7 85  2 97 48 55 78 31
            Card 131: 85 86 44 10 26 96 36 58 81 34 | 11 45 40 63 86 58 42 56 67  5 34 36 96 60 10 81 33 82 72 26 94 85 44 80 69
            Card 132: 98 21 79 52 33 11 43 57 69 76 | 52 42 81 22 19 91 43 55 54 11  6 33 69 56 68 97 76 21 40 94 79 98 23 57 28
            Card 133: 58 47 20 66 55 75  1 38 34 85 | 97 48 26 55  1 85 59 20 35 67 69  3 34 21 58 38 47 45 94 66 75  6 83 19 14
            Card 134: 65 61  7 13 39 46 75 81 15 66 | 32 15 44 13  5 39 63 90 11  7 40 94 81 61 62 21 77 84 65 20 46 71 66 97 75
            Card 135: 51 98 26 57 64 61 84 70 27 22 | 68 84 42 64 44 99 94 66  2 22 50 70 77 45 90 19 89  3 26 51 98 16 88 85 37
            Card 136: 89 73 74  1 38 34 58 39 25 10 |  8 30 58 91 12 21  5 56 32 16 79 50 85 18 22 26 78  3  6 11 72 25 67 70 59
            Card 137: 60 94 66  8 98 23 28 53 73  3 | 26  2 61 80 81 95  3 72 91 60 76 74 29 27 54  8 11 22 86 48 21 12 89 66 50
            Card 138: 48 37  7 21 17 82 60 53 52 40 | 62 58 82 15 78 81 40 17 53 86 88 87 27  5 45 11 97 21 72 26 37  6 48 44 13
            Card 139: 15 81  7 95 44 65 50 99 89 90 | 59 74 90 38 33 72 99 81 84 95  3 91 76 12 87 52 16  5 22 71 55 63 18 65 80
            Card 140: 44 30 47 21 34 82 87 10 35 36 |  2 39 73 84 62  4 88 45  9 94 97 75 80 35 36 96 56 81 49 74 71  5 54 29 44
            Card 141: 80 33 48 21 99 40 19 12 58 17 | 19 44  8 83 85 48 33 57 12 97 80 91 58 38 56  6 51 36 32 34 40 72 21 35 22
            Card 142: 45 10 13  1 32 86 34 48 20 58 | 43 78 84 27 75 49 37  8 85 89 76 38 50 11 80 69 99  7 67 70 65 88 23 98 63
            Card 143: 72 59 31 94 70 42 61 36 19 79 | 38 21 42  8 70 66  3 47 32 84 43 54 25 58  9  2 56 11 77 83 57 73 12 17 65
            Card 144: 12 10 83 81 76 26 71 37 41 60 | 56 54 16 77 25 67 83 41 78  2 92 26 76 97 47 96 87  8 91 12 90 43 34 70 81
            Card 145: 79 49 47 34 83 18 99 33 37 96 | 10 27 69 62  9 63 51 36 56 58 86  7 19 12 55 11 93 75 14 24 26 81 21 66 13
            Card 146: 22 58 55 97 33 34 29 19 13 48 | 83 96 92 68  9 57 93 55 60 17 22 24 56 39 69 97 94 33 98 91 79 14 23 20 74
            Card 147: 57 38 95 94 27 47 65 37 76 56 | 48 81 46 73 36 22 25 77 12 49 76 99 74 19 85 13 72 52 18  6 70 83 86 33 53
            Card 148: 53  1 91 42 36 45 85 61  6 94 | 10  4  5 63 48 84 38 33 88 11 57 40 20 49 43 23 66 79 65 45 90 85 50  7 28
            Card 149: 46 73 61 66 76 41 92 47 96  6 | 31 33 56 81 57  2 18 86 30 95 48 85 43 12 21 25  4 50 42 90 10 78  7 45 89
            Card 150: 80 59 69  9 35 33 65 84 78 18 | 53 76 21  4 48 67 81 24 50 23  1 93 83 37 62 63 90  5 47 32  3 45 77 43 41
            Card 151: 23 97 70 13 46 55 69 59 92 25 | 44 11 26 46 64 21 48 20 93 65  1 19 75 91 60 63 72  5 49 18 12 57 89 45  6
            Card 152: 97 49  2 99 84 14 73 94 63 85 | 57 40 75 97 66 93 14 98 11 85 76 73 63 86  2 89 72 49 21 91 84 36 99 78 94
            Card 153: 38 65 69 70 87 32 42 15 66 46 | 17 81 66 93 91 69 28 42 70 15 47 46 58 67 87 44 43 38 32 65 82 83  3 25 60
            Card 154: 64 74 31 71 27 39 48  8 24 69 | 77 17 74 98 39 82 65 34 69 18 54 35 48 53 14 24 56 64  8 71 31 12 75 27  3
            Card 155:  1 50 20 56 26 18  8 49 90 42 | 81  4 59 55 30 29 54 88 92 86 69 38 10 39  3 27 90 37 21 73 77 28 45 61 75
            Card 156: 70 23 94 93  5 64 37  2 61 55 | 52 37 11 46  5 66 86 23 94 70 61 72 38 97 26 71 19 57 54 36 29 74 65 27 30
            Card 157: 57 75 87 24 66 69 74 35 70 33 | 98  4 12 54 68 97 34 72 84 88 22 36 31 26 51 96 38 61  7 25 83 40 86 85 27
            Card 158: 87 17 36 56 98 96 81  5 77 55 | 88  5 98 95 17 12 61 55 75 10 87 90 56 85 36 46 67 14 81 66 37 96 77 82 76
            Card 159: 42 80 75 19 17 36 71 60 43 27 | 10 67 80 38 54  3 77  8 52 83 46 96 49 59 39 15 47 86 30 48 81 33 61 82 20
            Card 160: 29 93 28  2 59 53  6 21 70 40 | 73 34 65 35 16 27 38 19 60 42 80  3 40 24 83 57 55 12 75  9 78 81 52 44 21
            Card 161: 76  8  7 68 46 56 35 93 47 71 | 52 28 53 27 96  2 59  3 66 14 84 22 80 21 97 60 72 44 29 57 83 25 73 90 38
            Card 162: 69 61 44 13 30 32 83 89 33 10 | 89 42 80 61 24 82 13 76 53 74 65 30 99 72 29 62 70 81 86 57 84 33 87 83 44
            Card 163: 52 34 90 96 27 65 67 95 25 10 | 95  2 68 17 65 11 48 13 78 35  9 32 26 66 71 98 30 70 76 91 96 24 25 52 62
            Card 164: 46 70 42  5 60 69 58 34  6 57 | 16  7 41 74 76 36 10  3 47 51 62 67 94 25 28 83 40 12 71 45 48 84  2 90 63
            Card 165: 96 21 39 79 45 38 72 64 17 56 | 82 97 93 27 46 47 63  6  1  9 19 89 33 84 48 43 94 62 55 86 85 52 80 70 16
            Card 166: 83 19 94 46 15 22 35 90 62 28 |  4 20 90 73 28 15 59 42 43 67  3 94 36 88  8 31 45 10 12 40  5 17 74 98 71
            Card 167: 18 95  4 58 62 64 34 77 93 65 | 68 62 53 35 71 56 88 79 82  2 29  9 86 93 38 74 59 16 60 24 32 43 26 15 65
            Card 168: 53 79 20 89 32  3 81 23 64 22 | 25 69 59 80 99 34 90 21 26 95  2 18 20 71 11 75 19 17 82 85 43 56 77 78 84
            Card 169: 82  7 10  3 73 72 52 47 95 31 | 70 49  5 28  7 98 25 82 44 21 62 48 20 79 45 22 58 27 69 55 29 24 66  2 51
            Card 170:  8 97 49 33 48 28 27 74 51 73 | 34 77 84  5 75 41 31 95 30 96 49 52 11 32 23 79 37 63 62 10 59 42 86 56 45
            Card 171: 54 13 23 70 27 83 69 64 71 51 | 66  2 40 37 75 34 96 32 77 16  5 26 79 74 38  7 22 12 20 62 29 19 14 31 21
            Card 172: 60 58  7 78 52 27 83  2 61 44 | 75 55 84  8 27 50 52 73 83 13 47 62  2 23 40 90 71  5 65 61 48 57 44 58  6
            Card 173: 59 66 95 19 21  1 76 55 56  2 | 29 69 92 64 84 12 94 24 46 59 10 44 79 76  9 35 45 36 27  2 14 78 73 17 83
            Card 174: 86 87 96 80 61 11  9 67 93 15 | 15 49 74 86 41 38 25 88 87 69 35 11 61 59 80 96 19 26 21 67 93 47 78  9 90
            Card 175: 76 63 41 25 58 56  8 43 39 98 | 89 63 57 36 58 44 11 74 49 83 52 56 76 90 41 98  8 43 25 45 39 54 53 12 35
            Card 176: 80 76 51 23 90 68 96 16 34 77 | 76 15 80  8 37 39 13 30 16 46 51 74 34 83 24 77 95 90 23 43 59 79 96 98 68
            Card 177: 59 76 78 46  8 77 95 24 20 19 | 19 46 42 20 59 32 25 89 96 29 77 64  8 79 95 45 90 21 75 76 50  7 78 24 34
            Card 178: 73 16 95 54 99 89 41  3 70 17 | 27  3 89 72 78 73 56 16 20 17 31 79 10  9 88 95 54 41 83 11 65 74 57 93 49
            Card 179: 80 45 65  5 83 97 56 64 38 86 | 83 75 86 52 89 73 35 64  3 18 79 24  5 16 56 80 54 38 32 36 82 45 58 65 97
            Card 180: 16 64 79 20 71 76  3 22 52 35 | 29 79  3 35 66 58 26 76 80  8 16 68 33 93 46 20 71  5 15 69 88 70 64 22 52
            Card 181: 59 98  2  8 73 47 13 95 56 41 | 38 53 79 17 55 88 86 84 45 54 31 82 57 16 37 49 26 58 43 24 83 65 21 50 34
            Card 182: 96 57 17 81 63 49 31 71 12 72 | 98 57 74 82  6 58 14 29 83 72 50 46 31 93  3 51 77 19 96 48 73  8 28 61 92
            Card 183: 12 70 11 99 88 49 96 14 35 61 | 36 34 85 66 48 29 60 69 53 22  7 46  4 30 67 13  1 90 38 42 71 17 26 91 94
            Card 184: 34 65 59 61 70 79 18 21 89 44 | 33 99 82  3 96 19 59 45 23 80 26 81 54 95 75 27 74 21 98 40  5 77 22 60 72
            Card 185: 74 82 42 59 48 32 11 97 55 96 | 86 63 26  5 74 88 11 73 34 60 82 32  9 49 17 48 44 75 52 62 10 53 91 42 61
            Card 186: 18 17 57 64 77  4 28  3 81 61 | 16 55 18 84 71 10 96 92 91 59 11 43 20 52 63 69  5 25 35 33  7 57 87 12 76
            Card 187: 67 46 20 21 27  2 49 76 16 66 | 15 72  4 42 47 31 83 14 13 78 28 73 79 64 44 77  8 86 63 25 19  9 95 11 39
            Card 188: 28 72 26 81 55  4  6  7 89 63 | 10 76 20 42 93 18 74 95 79 13 78 39 98 65 97 73 28 96  7 30 69 59 72 92 41
            Card 189: 41 93 90 88 66 85 76 80 40 27 | 23 79  9 18 12  4 42 22 85 10 95 19 74 93 24 49 99 94 14 78 73 35 80 60 28
            Card 190: 77 44  3 80 29 72 95 64 11 78 | 17 34 76 68 90 86 81 88 62 40 30  9 87 12 55 94 32 97 56 71 85  7 21 57 25
            Card 191: 68 75 18 71 84 37 19 29 69 92 | 10 33 86 73 13 70 30 98 61 79  2 51 59 49 36 48 68 55 14 95 91 27 24 12 99
            Card 192: 12 53 70 80 42 90 81 91 94 24 | 15 58 32 82 33  3 35 55 95 44  2 43 50 68 49 45  4 51 62 37 39 31 98 29 30
            Card 193: 92 16 46 79  5 86 63 62 17 77 | 18 84 59 79  8 63 64 13 26 62 45 52 44 30 54 70 27  5 71 86 78 90 87  1 82
            Card 194: 37 50 31 16 82  1 44 48 99 19 | 45 64 15 88 24 31 50 83 57 14 79  8  5 12 81 52 59 41 40 17 33 16 54 75 69
            Card 195: 88 67 18 56  3 40 96 14 10 43 | 69 10 66  2 40  6 89 79 14 43 22 19  8 83 46 72 53 18 23 87 37 41 51 88 96
            Card 196: 59 20 34 25 68 50 33 89 14 54 |  2 20 11 68 70 65 90 25  3 98 59 14 83 86  4 54 57 62 50 78 73 21 48 58 74
            Card 197: 27 87 77 22 81 69 41 12 92 39 | 83 11 27 60 92 53 51 76 12 41 25 71 99 77 16 10 39 94 22 96 65 61 87 85 81
            Card 198: 38 72  9 16 22 41  4 78 71 92 | 96 95 47 39 92 77 49 28 54 76 91 30 58 14 31 88 48 37 84 65 11 45 35 97 64
            Card 199: 76 56 21 70 87 33  1 89 31 11 | 14 56 74 75  5 71  1 18 20 44 66 89 31 94 87 68 24 23 26 80 39 33 16 37 35
            Card 200: 25 57 85 89 11 39 16  2 75 79 | 66 46 93 87  7 36 59 85 25 70 39 18 63  2 75 35 72 56 52 11 14  9 78 79 28
            Card 201: 94 12 32 65 45  3 18 62 85 95 | 70 77 64 30 14 67 94 34 93 16 66 44  7 20 59 31 39 49 81 56 89 83 15 97  2
            Card 202: 74 94 97 79 22 32 84 54 57 25 | 29 74 83 60 70 88 32 33 61 95 86 85  6 71 31 90 72 87 26 64  2 13 18  5 14
            Card 203: 21 93 57 91 71 95 75 79 94 12 | 11 58 33 53 62 19 52 23 38 47 48 83 42  2 98 46 29 34 84 41 74 32 70 96 18
            Card 204: 81 69 71 85 12 99 30 21 98 95 | 71  5 85 93 74 43 90 12 35 82 83 73 54 86 65 49 20 30 68 17  8 76 40 84 11
            Card 205: 49 96 70  6 60 88 43 68  2 47 | 99 86 80 37 50 12 23 74 93 20 36 29 16 13 17 94 33 25 73  8 53  1 22  5 87
            Card 206: 97 64 23  8 74 63 32 27 40 67 | 96 93 59 16 37 48 53  9 54 82 79 39 85 27 11 41 31 10 78 30 13 60 88 51 80
            Card 207: 61  4 59 62 99 32 36 11  7  5 | 54 95 25 60 46 50  1 38 32 39 93 79 49 67 57 73 87 77 45 33 16 55 75 58 82
            Card 208: 22 12 64  5 63 62 72 86 49 47 | 17 35 69 33 68 67 78 82 14 61 70 95  3 40 71 91 43 75 44  6 58  9 30  8 66""";

}
