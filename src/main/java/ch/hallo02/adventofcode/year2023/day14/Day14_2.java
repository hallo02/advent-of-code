package ch.hallo02.adventofcode.year2023.day14;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class Day14_2 {

    //to high 90182
    //win 90176
    public static void main(String... args) {

        List<String> fieldTemp = puzzle.lines().collect(Collectors.toList());
        char[][] field = toCharArray(fieldTemp);

        for (int c = 1; c <= 1_000_000_000; c++) {
            toCycle(field);
            long currentWeight = getWeight(field);
            if (c % 1000 == 0) {
                System.out.println("(--" + c + "--" + currentWeight + "--)");
            }
        }
    }

    static char[][] toCharArray(List<String> field) {
        char[][] arr = new char[field.size()][field.get(0).length()]; // y,x

        for (int l = 0; l < field.size(); l++) {
            for (int p = 0; p < field.get(0).length(); p++) {
                arr[l][p] = field.get(l).charAt(p);
            }
        }
        return arr;
    }


    static void toCycle(char[][] field) {
        moveNorth(field);
        moveWest(field);
        moveSouth(field);
        moveEast(field);
    }

    static long getWeight(char[][] field) {
        long sum = 0;
        for (int l = 0; l < field.length; l++) {
            for (int p = 0; p < field[0].length; p++) {
                if (field[l][p] == 'O') {
                    sum += field.length - l;
                }
            }
        }
        return sum;
    }

    static void printField(char[][] field) {
        for (int l = 0; l < field.length; l++) {
            for (int p = 0; p < field[0].length; p++) {
                System.out.print(field[l][p]);
            }
            System.out.println();
        }
    }

    static void moveNorth(char[][] field) {
        for (int l = 1; l < field.length; l++) {
            for (int p = 0; p < field[0].length; p++) {
                int lFrom = l;
                int lTo = lFrom - 1;
                while (field[lFrom][p] == 'O' && lTo >= 0 && field[lTo][p] == '.') {
                    field[lFrom][p] = '.';
                    field[lTo][p] = 'O';
                    lFrom--;
                    lTo--;
                }
            }
        }
    }

    /*
    111
    222
    333
     */
    static void moveSouth(char[][] field) {
        for (int l = field.length - 2; l >= 0; l--) {
            for (int p = 0; p < field[0].length; p++) {
                int lFrom = l;
                int lTo = lFrom + 1;
                while (field[lFrom][p] == 'O' && lTo < field.length && field[lTo][p] == '.') {
                    field[lFrom][p] = '.';
                    field[lTo][p] = 'O';
                    lFrom++;
                    lTo++;
                }
            }
        }
    }

    /*
        111
        222
        333
         */
    static void moveWest(char[][] field) {
        for (int p = 1; p < field[0].length; p++) {
            for (int l = 0; l < field.length; l++) {
                int pFrom = p;
                int pTo = pFrom - 1;
                while (field[l][pFrom] == 'O' && pTo >= 0 && field[l][pTo] == '.') {
                    field[l][pFrom] = '.';
                    field[l][pTo] = 'O';
                    pFrom--;
                    pTo--;
                }
            }
        }
    }

    /*
      111
      222
      333
       */
    static void moveEast(char[][] field) {
        for (int p = field[0].length - 2; p >= 0; p--) {
            for (int l = 0; l < field.length; l++) {
                int pFrom = p;
                int pTo = pFrom + 1;
                while (field[l][pFrom] == 'O' && pTo < field[0].length && field[l][pTo] == '.') {
                    field[l][pFrom] = '.';
                    field[l][pTo] = 'O';
                    pFrom++;
                    pTo++;
                }
            }
        }
    }


    static String mPuzzle = """
            O....#....
            O.OO#....#
            .....##...
            OO.#O....O
            .O.....O#.
            O.#..O.#.#
            ..O..#O..O
            .......O..
            #....###..
            #OO..#....""";

    static String puzzle = """
            ..O.O....O...O...O...O.#O#.#.#O#O..#.#O....O...#..#O#...............#O..#.#..O..O.##.#O#..O...#.....
            .#O.#.#O.##..O..#.......###...#.O#.##....#O#O.OO..##...#..O.O...#O.......O.O.OO##...#.##OOO.O.#..#.O
            #.##..O.O.....#.OO....O#..OOO..OO..O....#..#...O..#..O..O.O#O.....O...O.#.O.O......O..OO..O...O...OO
            O.....#.O...#....#.#O.#.O.........O...O..#.O.O...O#..O.OO......O.#O......O#.OO.#.##..OO.O#O.O.#.....
            #O...#..........#..#......O..O..#......#O..OO...#O.O.............O..O.......#....O.OO#..OO#....O#.O.
            ##O.O.....OOO..OO..O...O.OO...#.O.O...#..O...OO...O..O.#OO.#........O........O.O....O...O...........
            O..O...#.O.##.....OO.O#.......OO...OOO.#..#OO....#..O#...O.....O..O.#####..........O#..........O#...
            OO......OO.O...#.#......#.#....OO###...O####OO..#...........##..O#O...#.........#..#....#OO.O.#....O
            .##..O.#...O.#.O#..#.O.#...OO##O.....O.O.......O...........##...O....#OOO.....#...O.....O.O##.O###OO
            O..O...O.O...O#O.OOOO.O.O......O#...O...#.#.......O#.O...#..#O.....#........O#...O..O..O.#.#.#O#OOO.
            O#......#.#.O...OO#..#.#.#.O#.O#.......O#...O.O..O..........O........#.#O....#..OO..#..O..#.O..##...
            OOO.#.OO.O..OO.OO..O....O...OO..#..#OO.O...#....#.OO.#..O.O#.O.#OO.O.....O......#.O..O#O...OO##.#...
            .O#O..#OOOO#.O##.........OO#O#.#.O#..#..OO.....O.##.#..O.O....#...#..O....O.#.O#..O....O.O.....O.O..
            ..O#.OO..........#OO...O..O...#.####.#...#.......#####O.#.##..#O...O...O.....O......#.O.....O.......
            .O........#.O.###OO..............#.......O...O.....OO#...#.O...#.O.O.O.O.......#.O..#O....##..#..O.#
            .#.O#..#....O...#..O.#.O....O#......#...#...OO.O....#.O.O..#.......##OO..O.....OO...OO#...OO...###..
            .#O.#.#...O.#..#.......#....#O.O.#....OO.O##.O.#..OO.O......##.....OO...O#..O.O.OO.#.O.O....#.......
            O..O.OO.O.....O..O..#....O.......O.##O#...O.....O#OO#.....O..O.....O..#..O...............##.....#O..
            ....O..#OO#..#.#.O.#.#.OOO.OO#....OOO#....#.........O..O.#....#....OO.O.##...##O.O..#.O..#....O..O#.
            #O.O.O.O.#O##...O..O##.O#....#......O...#.O..O.O....O.##O....O..#...O....##.#O.O#O...#.O#.#...O..O..
            #..O.O..O##.O..O....O..OO#.OO........O#....O....O............O#.#.OO..#.#O.#....##.....OO..O...O...O
            .O..OO.OOOO.#.#.O.#.O..O##....##O..##..O.#O.O#.#...#....#.....#OO..O#O......O........O...O........#.
            ...OO.O###.O.###.......#O#..O..#.#O...O#...O...O#..##..OO.O...O......O....#.#....OO.O.O#...OO...##O#
            #..OO.....#.O.OOO.#...O.......O......#O...##...#O##..OO.#.O.........O.O...O...O..##...O..#..O#.....O
            ...O.#.#.O....##O...#O#...O....O..OO..#O.O....#O..#...O.OO.....OO.OOO.O.O.#.....OO#O.##..#..#.O#...O
            ..O#.O......##O#.OO..........#......#.OOOOO...#.O##......O...O.#OOO.O#O..#..O....#...O....#O..O.O...
            ...#...##O.O......#O...O#O...##OO.O......O..#O#..O.....#.OOO..#.O.O#..###.O....O.O.OOO....OO#O....#.
            O#O..##..#O.#..........OO...O.O..###.O..O#.O.O.O.#.#.O.#.O...O.OO....#.#.....##..OOO#..OO......O#.##
            O..O#O..#.#O.OO...#.OO.OO.##.O....#.....##O..O#..O.O.OO##.O#O.O#.O.#O..O.#.O....O#.#.O....#..OO....O
            .O..##O#OO...O...O.....O....O.O.O........O........##O#OO.O.OO.O.##...##......O....#.O.O....O#.O...#.
            .O.......#.#...O.....O...#........O#....O#.OO....#...O#.#O...#..##.#....##.OOO.#......#..O...OOO.#.O
            ......#O..##........##O#.......###.....O..OOO.......#.OO.#.....#...#.##.#..O......#O...O.O..O.....O.
            .OOO#..#.....#.....#......O#O#....O.OOOO###.....O....OO.#O#...#.OO......O.O........#OO#.O..O........
            #O#.#.OOO###...O..OO.O...#.#........#.#.#......OO#.....O...O....O.#....#..#..##.#.OOO#....#.#.O....O
            #O#.#.##....#.O....##.....##.O.O.OO#.....#O...O#.....#..O.....#.#......#O..O...O.....#..O....#...#..
            .O.......#.....O.O#.#.O.....##..#OO.#OO.O.O.#O.O.#O##OO.#..#O.O.....#......O#........#.#O...#.#.O...
            #..O.##......O...O.O#..OO#...#..O..#OO.#O.O#....O#..#....O....O..#O..#.#......O...#..OO#....O.O....O
            .#......#.#......#O............#OOO......#.OO..O#.O#.......#....#....##.O.......#...O.O#...O...#O..#
            O...OO..O#......#.O........#.#....OO.#......O....O##..........O#..#....#O...O#.O..#....OOO..O....#O#
            O...O...#.O.O.O.OOO..OO.##.O....O......#.#O...#...O...O#....O.#...#.......#.##....#.#.O.OOO..O...O.O
            .#.O#...##O#.O..O....O.#.....#O.....#.O.....#...O#.O.O..#....O#OO..OOO..#O.OO.O...OO.OOO.#...##.O..#
            ........O#O#...#..OOO#...#O.O#..#.O.....O.OO.O#O..##.O.....O.#OO.........O.#OO.....OOO.....O.O.O.#..
            .#....OO.#............O#..#.....OO....O.O.#..#...OO..#....#...#.#O....##..O....#O..#OO..O.....O....#
            O..#.....O.O...O........O....#.#O...#......O....O......O......O..#.OO......#O...#..O.##......O#....O
            ...O.###..#...#.#..O.#.............#OO.#...#...##.#.O#.........O..##.##.#....O.....O.......O..O...O.
            .O##....O...#....OO.##.#...O......OO..........#.O.......OO#.O.#..#.OO...#......OO#O.......#..#...#..
            .#.O..#..#.OO.....O..O#.O..O....#.....O#O.OO.#.OOOO.##.O..O...O.O..#....O.....O.#...O.#...#.....O..#
            .#.#O..#....#O..O.OO..O.....O....###..#...O.#....O#O...#....##...##.O.##..O...OOO.#..#O.....OOO#.O..
            O..#O....#..............O.O.O..O.O.#..........O.....O#.O#.O..O.#.......O.OO...#.....##.#..O.#.#.#..#
            #O..O..#.O.O..O#.OO....#O..#.....O..#..OO.#..##O.........#...O.#.....#..#.OO..O.##...#...#O.#O##..OO
            #.........O...#..O.##O......#.O..##..#O..........#.#O#O........##....O....O....#..#...#O......O#.O.#
            #.#..O.O.O.#.OO#.#..O....O#O.O.O.O..#....#..#.....#..O......O#...O.O.O....OOO##O.###.....O.O#.#..#.#
            O...#OO..#...O..O..#..OOO.#....#..#.##...#..#....OO.O.O............#.#O#O#OO.OO...O.##O.##O#O....OOO
            O..O.......O.#OO.O#........O###...O........O..#.O.OO.O...OO..OO.#O.O.#O.OO.O....OOO#.#.O.......O.O.O
            #O..#O.......#..##.#....O.O#O#O.O#OO..O...O.O.O.OO.#..O#.......OO.O.##.O....O...#O......O....#O.O...
            #..O........#..#..#O.OO##..OO...#..O#.O##....#.#.#.#...#....O.....O.....O...OO#O...#..O....#..##..O.
            ..O.....O.OOO#O....#.#.......#.OO....O.....OO#..O.O#.#.O...#...#..#.#OO..O.....#.O.....#...O...#.O#.
            #.....#.....#O.#.O#O.O....#.....O#O#O#OO...O.....O#.........O..#....O.....O.......O.O#OO##....O#....
            OO....#..O.#.O#....O#.O..#.#....#.#..#...#....#..OO......OO.O..###O....OOO.O..OOO#.#.O...#.OO.#..O#.
            .##.#O...#......####......#...#..#.O.......O#..#.#...O#...O..O.........O##.#....O#OO..OO......O..##.
            ...O..#.O.O..#O..#O............#O#O..#.#...O..#......O..O...O##....#.#.............O..#...#......#..
            #.#..#...##....#O....O.O.O.....#...#O..O.#..#.#O.O.#OOO.....#..#..#OO..O#........#.#.O..........O...
            ...#.O#......#...O...OO..#.#O.O..###O#O#...#.#..#....O#..O.#OO...#...O.##O...#.O..O#..#.O.#..#..OO..
            ...#....O...#.#.......OO#..OO.O..####.....##O.#...#.O....O......#......O#...#..#.O..O.OOO.O......#..
            #..##..O..O....O..#O.O.O..O#.#.#..O..#.#...O.......OO#.OO...O..#.O#..O...OOO..O...O#..O....#....O...
            .#.#...#.O.O..O..#..O........O....O#.#...O.O...#...O.O##O....#.#.O...#.O..#OOO..O.....#.O....##..##.
            .......#OO..O..#.#....O.....O#.OO.O....O.........##.....OO......O...O#.....##..O#...#....OOO...##...
            ..#O...O.....O.O.##O..O.....O...O#O......#.........O..OO.....O.#.#OO..#....O.#...O..#.....#..O...O#.
            .....O.O...#O.##.#O#..#.OO#.O..#O.O.#.OO.#OO...OO#O.##.O.#.O.##.#..#.#.............O.O.#.O..O..#...#
            ...O.#.....#.#.#..OO.O.O..#.O.....#.##......OO.#....OO.#.#O..#.O.....#.....##..O.#O#..OO.........#..
            .O.#O.........#O....O.O....#.O.O..#.O#O..O.#.OO......O#....O#O.O.............O..#...O.OO#...O..O...O
            .......#...#....#.....O......O.OO.O....O#.O...#....#...........#.O#..#..OOO.#O..#.O...O#..##.....O.#
            ........#....##...O..O.............#.O...O..#..#...O.##.#...O...#...#.O..#....###.#O.#.O.O..O....O.O
            O.#....OOO..O.O.##.........O...O...OO#.....#O..OO..#.....#O..O#.O#.#O.#OO#...O#O....O...O...O......O
            .O..........##.O.#..OO....#.O..........O.#....#.O...O.O.....OO.#..O...O....##...#OO..#...OO#.O#O.#..
            O......O#O....#.##O.##OO.#....OO.....O.O.OO..O#.....#..OO.O..OO......#..O.OO..OO..O...#..O...#O.....
            .###....O.....#O..#O....O.....#.OO..##....#.....O.#......#.....O##O...OO...O##O#....O#.........O#O#.
            OO.#O##....#O.#...O........OO.....#..O...O..#.O#.#.#..OO#O.##.....#.....O.O....OO.....O.O....#...O#.
            ....O#OO..OO......#O.....#.O...OOO..OOO.#O....O..#.#.###..O..O..O#..........#..OOO...OO.O#O.#.#..#O.
            O....#O.O.O.......#......O.......O.O...O...O....##O..O.OO#O....O.#............O..#O.##...O..........
            O.......O....O##....#......OO..##.O....#O..##..O......O.O.......O#.#...###..#.....##..##.....O......
            .O..O###O.........#.O.....OO..O.#.O.O...#O#.O.O..##..O..O...O..#...#O.#..#..O..#....O..O.O#.O#.O.OO.
            ..#.........O#..#OO......O.O#..O#.........O.#OOOO.....O..#O..#..#..OO.OO...OO...#..OO#...##..O...#..
            ..OO..#....O..O.O..OOO..#O#O##..#...O#O...#....O..O..#.O.OO..O.O......#O........#.....O.#...O#O...OO
            ..#......O##O.....OO...##......O...OO#....O..#OO#..#.O..O.#.......#..O.#.O#....#....#.OOO.#.....#...
            ..#.O.O.O.#....#.#.O.##.....#.O#.OO..OOO..O......O.........#....O..#..O.###.#..##O#...O#.O.O.O.#...#
            #..O..O..#.#.O...#O#..O.....#O.O.....#............O..#.O#.....O....O..O.OO.O..#....OO.....###..#....
            .O.O.#.....O#O##...#..#...#........#..O#.....O..#..O...O.#.....OO..O.#.##....OO#.#.....O..O....O.O..
            ........O..O.#..O#O...#........O.#O#...O#O##......O..#....#...O...O..O.O..##.OO....#.....#.O...#O...
            .O.O..##O..O...OO...O..O.O#O...OOOO..O....O........O#...O..O#....O...O#OOO.O##....##..O.....OO.O#..#
            .....#O.#..O......O...OO.O..#..#O....O....O....O#....O.O..O...#O.#....##......##..#..#O..#..OO...O.O
            ...#O.#.#..OO..O..........##..O#.#OO.......#...#O###.#O..O#O...O...#..O....O#O#O.#.....O.O.O#...O..#
            ##..#......O##..O.#...O....OOOO.....#....O.#...#.........#..O.#..O.....#...O...O.......O#......O#O..
            .....O......O..##O#.....#O#...#O....O..O..O.......O#O##....OOOO..##...O.O#...OOOO...#OOOO##O..##....
            #...O.O.O#..OOO...#O..O##.....#.#..#O...O###OO...O.OO.......O.O..#...###...........O..#....#.##...OO
            O.OO......#....#........O#.#.#O.....#..........O#.#..##O...#..OO...#OO.#..O.#O..#........O.O..O#.O##
            #.##...O.#.....OO...O..#.#.O.O.O.#.#O...#....#.#......#...#...#.O.#O#..#..O...#.OOO..#OO##..OO...#.#
            O.....#.....#.O.O...O#.#...OO..OOO..O..O.O.#...O..#....O.#..OOO.O.O#O..OO#O..OO.O...O.........##.O..
            ..#....O........#.#O....O..#....O.###OO..#.#O.O..O.....###..OO.O..##...#.#..#O..O.....O.#.#O...O....
            .O.OO..O#........O#..O..##...#O.......#..#.....#.........OO..#.O..OO#O..#....#OO......O............O""";
}


