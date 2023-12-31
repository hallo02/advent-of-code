package ch.hallo02.adventofcode.year2023.day11;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import static ch.hallo02.adventofcode.year2023.day11.Day11_1.Element.EMPTY;
import static ch.hallo02.adventofcode.year2023.day11.Day11_1.Element.GALAXY;

public class Day11_1 {
    // good: 9329143

    public static void main(String... args) {

        List<List<Element>> space = puzzle
                .lines()
                .map(Day11_1::parseSpaceLine)
                .collect(Collectors.toList());

        doubleEmptyColumns(space);
        doubleEmptyRows(space);

        printGalaxy(space);
        System.out.println("result: " + sumBetweenAllGalaxies(getAllGalaxies(space)));

    }


    static long sumBetweenAllGalaxies(List<Coordinates> coordinates){
        long sum = 0;
        for(int coord = 0; coord < coordinates.size(); coord++){
            for(int coord2 = 0; coord2 < coordinates.size(); coord2++){
                if(coord == coord2) continue;
                var g1 = coordinates.get(coord);
                var g2 = coordinates.get(coord2);
                sum += Math.abs(g1.column-g2.column) + Math.abs(g1.row - g2.row);
            }
        }
        return sum / 2;
    }

    static List<Coordinates> getAllGalaxies(List<List<Element>> space) {
        var galaxies = new ArrayList<Coordinates>();
        for (int r = 0; r < space.size(); r++) {
            for (int c = 0; c < space.get(0).size(); c++) {
                if (space.get(r).get(c) == GALAXY) {
                    galaxies.add(new Coordinates(r, c));
                }
            }
        }
        return galaxies;
    }

    static class Coordinates {
        public int column;
        public int row;

        Coordinates(int row, int column) {
            this.column = column;
            this.row = row;
        }
    }

    static void printGalaxy(List<List<Element>> space) {

        var rowIterator = space.listIterator();
        while (rowIterator.hasNext()) {
            var columnIterator = rowIterator.next().iterator();
            while (columnIterator.hasNext()) {
                System.out.print(columnIterator.next() + " ");
            }
            System.out.println();
        }
    }

    static void doubleEmptyRows(List<List<Element>> space) {
        var rowIterator = space.listIterator();

        while (rowIterator.hasNext()) {
            boolean rowIsEmpty = true;
            var columnIterator = rowIterator.next().listIterator();
            while (columnIterator.hasNext()) {
                if (columnIterator.next() == GALAXY) {
                    rowIsEmpty = false;
                }
            }
            if (rowIsEmpty) {
                var newRow = new ArrayList<Element>();
                space.get(0).forEach(o -> newRow.add(EMPTY));
                rowIterator.add(newRow);
            }
        }
    }

    static void doubleEmptyColumns(List<List<Element>> space) {

        for (int c = 0; c < space.get(0).size(); c++) {
            var columnIsEmpty = true;
            for (int r = 0; r < space.size(); r++) {
                if (space.get(r).get(c) == GALAXY) {
                    columnIsEmpty = false;
                }
            }
            if (columnIsEmpty) {
                for (int r = 0; r < space.size(); r++) {
                    space.get(r).add(c, EMPTY);

                }
                c++;
            }
        }

    }

    static List<Element> parseSpaceLine(String l) {
        return l.chars().mapToObj(e -> e == '.' ? EMPTY : GALAXY).collect(Collectors.toList());
    }

    enum Element {
        GALAXY('#'), EMPTY('*');

        char e;

        Element(char e) {
            this.e = e;
        }

        @Override
        public String toString() {
            return this.e + "";
        }
    }


    static String miniPuzzle = """
            ...#......
            .......#..
            #.........
            ..........
            ......#...
            .#........
            .........#
            ..........
            .......#..
            #...#.....""";

    static String puzzle = """
            .....#..........#...........................................#................#.....................................#...............#........
            .........................................#...........................................#........................................#.............
            ..........................#........................................................................#........................................
            ..................................................#.......................................#.............................#...................
            ..........................................................#............................................................................#....
            .......................................#............................#...........................#...........................................
            ...................................................................................#..................#............#........................
            ...#..........#.....#.......................................................#................................#................#.............
            ................................................................#...........................................................................
            .........................#.....................#......................#..................................#...........................#......
            ..................................#.........................................................#...............................................
            ...........................................#................................................................................................
            ............................................................#............................................................................#..
            ........#..................#.............................................#......................#..................#........................
            ....................................#...............#...............#........................................................#..............
            ..................................................................................#............................#............................
            .#...........#......#....................................#.......................................................................#..........
            .........................................#............................................#.................#............#......................
            .................................#.............................................#...........................................#..............#.
            ....#.......................................................................................................................................
            .........................#.............................#..........#.....#...................................#...............................
            ............................................................#....................................#..........................................
            .................#............#...................................................................................................#.........
            ......................................#...............................................#...............#.................................#...
            ...............................................................#..................................................#.....#.....#.............
            #.....................#..............................#...........................#..........................................................
            ...............................................#.....................#...............................................................#......
            .........................................#...............#..................................#...............................................
            ......#............................#........................................................................................................
            ..........................................................................#...........................................#.....................
            ..................#...................................#.............................#..............#........#...................#...........
            ...........................#......................................#.........................................................................
            ..............................................#...........#....................................#...........................#..............#.
            ....#..................................#....................................................................................................
            ................#.....#.....................................................#.............................#........#........................
            ...............................................................#......#.....................................................................
            ............................................#..................................................................................#............
            .......#........................#....................................................................................................#......
            ..............................................................................#...............#...........................#.................
            .....................#.........................#............................................................................................
            ..........................#.................................#.....#...............................................#.........................
            ...................................................................................................#..............................#.........
            ..................#...............#.......#............#....................................................................................
            .........................................................................................#....................#...............#.............
            ............#............................................................................................................................#..
            ......................#......................#..........................#......#..........................................#.................
            ............................................................................................................................................
            ...#...............................................................#..............................................#.........................
            ..........................................#..................#.............#..........................................................#.....
            ..................#............................#............................................................................................
            ......................................................#.................................#.........#.........................................
            ......................................#.................................#.........#........................#................................
            #..........................#..........................................................................#....................#................
            ...........#................................#...............................#........................................#.............#........
            ...................................#....................................................................................................#...
            ............................................................................................................................................
            ...............#.........................#..................................................................................................
            .................................................................#.......#......#...........................................................
            #.....................................................................................#........................................#............
            .........................#...........................................................................#...............................#......
            ....................#..................#..........#.........#...............................................................................
            ..........#......................#.........................................................................#...............#................
            .............................................#................................................#.............................................
            ......#...............................................#........................#...................................................#........
            ............................................................................................................................................
            ..............................................................#..........................#..................................................
            ............#.....#....................#...........................................#........................................................
            ..#................................................#......#....................................................#............................
            ..........................#...................................................................................................#..........#..
            ....................................................................#.......................................................................
            ....................#.........................#........#...........................................................................#........
            #.......................................#..........................................................#.....#..........#.......................
            ............................................................#............#............#.....................................................
            ............................................................................................................................................
            ............................................................................................................#........................#......
            ..............................#..............................................#.........................#.....................#..............
            .......#..............#.............#...............#.......................................................................................
            ............................................................................................................................................
            ..................#......................................................#....................#................#............................
            ...........................#.....#......#........................#.............................................................#............
            ............................................................................................................................................
            #...................................................................................#......................#..........#................#....
            ............#..........#......................#.............................................................................................
            .............................................................#..........#.........................................................#.........
            ....#.............#.........................................................................................................................
            ................................#......#.........................................#..................#...........#..........#................
            ............................................................................................................................................
            #..............#......................................#...............................................................#.....................
            ..........#..................................#..............................#...............................................................
            ............................................................................................................................................
            .................................................#..............#....................#.........#.......................................#....
            ............................................................................................................................................
            ............................................................................................................................................
            .............#............#................................#.........#......................#.............#................#................
            #..............................................#................................#...........................................................
            ..........................................#...............................#.......................................#......................#..
            ..............................#........................#..........#.........................................................................
            .........#.......#..........................................................................................................................
            .........................#................................................................#...................................#.............
            .............................................................................#.....................................................#........
            ....................................................................................................#.......................................
            ......#.....................#.....................................................................................#.........................
            ......................................#...................#...............................................................#.................
            ......................................................................#.....................#............................................#..
            ..#...............................#............#...............#...................#..............................................#.........
            ..............#..........................#.............#....................................................#.........#.....................
            ...............................................................................................#............................................
            ........................#.........................#...............#...........................................................#.............
            .........#..................................................................................................................................
            .....................................#...............................................................#......................................
            .........................................................#..................#...................................#...........................
            .................#..........................................................................................................................
            ..............................#.................#....................#...................#...............................#..................
            ...............................................................................................#.................................#..........
            .........#..........................................#.....................................................#.................................
            ....#................#............#..............................................#..........................................................
            ..........................#............#.............................................................................#......................
            ...............#...............................................#...........#...................................................#............
            .......................................................................................................................................#....
            ...............................#...............#.....................................#............#.........................................
            ..#.........................................................................................................#...............................
            ...........................#...............#..............................................#..............................#..................
            ................#......................................#.........................................................................#..........
            .......................#..............#...............................#.....................................................................
            ...............................................................#................#.........................#.........#.......................
            ...............................#............................................................#......#........................................
            ......#............#....................................................................................................#...................
            ...........................#..........................................................#................................................#....
            ....................................................#..................#...............................#....................................
            ..............................................................................#.............................................................
            ...#............................#..............................#..................................#.................#........#..............
            ....................................................................#.........................................#.............................
            ...........#.....#...............................#..........................................#...............................................
            .....................................#...........................................................................................#..........
            ....................................................................................................#...................................#...
            .....#..................#.....................#............................#......#...............................#........#................
            .............#...................................................#.............................#............................................
            ..............................#...........................#.............................................#..................................#
            ......................................#...............................................#........................................#............
            ...................#......#........................#.................#................................................#.....................""";
}
