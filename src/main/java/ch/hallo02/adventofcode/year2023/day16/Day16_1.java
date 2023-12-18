package ch.hallo02.adventofcode.year2023.day16;

import java.util.*;

import static ch.hallo02.adventofcode.year2023.day16.Day16_1.Direction.*;

public class Day16_1 {

    // not 401
    // not 6815, too low
    // win: 6921

    static Set<Tuple> visited;
    static Deque<Tuple> toDo = new LinkedList<>();
    static char[][] area;

    static class Tuple {
        int line;
        int position;
        Direction direction;


        Tuple(int line, int position, Direction direction) {
            this.line = line;
            this.position = position;
            this.direction = direction;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Tuple tuple = (Tuple) o;
            return line == tuple.line && position == tuple.position && direction == tuple.direction;
        }

        @Override
        public int hashCode() {
            return Objects.hash(line, position, direction);
        }
    }

    enum Direction {
        UP, LEFT, DOWN, RIGHT;
    }

    public static void main(String... args) {
        visited = new HashSet<Tuple>();
        area = getArea(puzzle);
        toDo.add(new Tuple(0, 0, RIGHT));
        walk();
        System.out.println(
                visited.stream()
                        .map(t -> new Tuple(t.line, t.position, null))
                        .distinct()
                        .count()
        );
    }

    static Tuple getUnseenTupleOrNull() {
        while (!toDo.isEmpty()) {
            var c = toDo.pop();
            if (!visited.contains(c)) {
                if (c.line < area.length && c.line >= 0 && c.position < area[0].length && c.position >= 0) {
                    return c;
                }
            }
        }
        return null;
    }

    static void walk() {

        var tuple = toDo.pop();
        int l = tuple.line;
        int p = tuple.position;
        Direction d = tuple.direction;

        while (true) {
            if (l < 0 || p < 0 || l == area.length || p == area[0].length) {
                var c = getUnseenTupleOrNull();
                if (c == null) {
                    return;
                }
                l = c.line;
                p = c.position;
                d = c.direction;
            }

            if (visited.contains(new Tuple(l, p, d))) {
                var c = getUnseenTupleOrNull();
                if (c == null) {
                    return;
                }
                l = c.line;
                p = c.position;
                d = c.direction;
            }

            visited.add(new Tuple(l, p, d));
            char current = area[l][p];

            // .
            if (current == '.' && d == Direction.UP) {
                l--;
            } else if (current == '.' && d == LEFT) {
                p--;
            } else if (current == '.' && d == Direction.DOWN) {
                l++;
            } else if (current == '.' && d == RIGHT) {
                p++;
            }
            // |
            else if (current == '|' && d == Direction.UP) {
                l--;

            } else if (current == '|' && d == LEFT) {
                toDo.add(new Tuple(l + 1, p, DOWN));
                l--;
                d = UP;

            } else if (current == '|' && d == Direction.DOWN) {
                l++;

            } else if (current == '|' && d == RIGHT) {
                toDo.add(new Tuple(l + 1, p, DOWN));
                l--;
                d = UP;
            }
            // -
            else if (current == '-' && d == Direction.UP) {
                toDo.add(new Tuple(l, p - 1, LEFT));
                p++;
                d = RIGHT;

            } else if (current == '-' && d == LEFT) {
                p--;

            } else if (current == '-' && d == Direction.DOWN) {
                toDo.add(new Tuple(l, p - 1, LEFT));
                p++;
                d = RIGHT;

            } else if (current == '-' && d == RIGHT) {
                p++;
            }
            // \
            else if (current == '\\' && d == Direction.UP) {
                p--;
                d = LEFT;

            } else if (current == '\\' && d == LEFT) {
                l--;
                d = UP;

            } else if (current == '\\' && d == Direction.DOWN) {
                p++;
                d = RIGHT;

            } else if (current == '\\' && d == RIGHT) {
                l++;
                d = DOWN;

            }

            // /
            else if (current == '/' && d == Direction.UP) {
                p++;
                d = RIGHT;
            } else if (current == '/' && d == LEFT) {
                l++;
                d = DOWN;

            } else if (current == '/' && d == Direction.DOWN) {
                p--;
                d = LEFT;

            } else if (current == '/' && d == RIGHT) {
                l--;
                d = UP;

            }
        }
    }


    static char[][] getArea(String input) {

        var lines = input.lines().toList();
        var area = new char[lines.size()][lines.get(0).length()]; // Y X

        for (int l = 0; l < lines.size(); l++) {
            for (int p = 0; p < lines.get(0).length(); p++) {
                area[l][p] = lines.get(l).charAt(p);
            }
        }
        return area;

    }

    static String mPuzzle = """
            .|...\\....
            |.-.\\.....
            .....|-...
            ........|.
            ..........
            .........\\
            ..../.\\\\..
            .-.-/..|..
            .|....-|.\\
            ..//.|....""";
    static String puzzle = """
            \\..................-...|..............................|./................-.....................\\.../..........
            .-....../.....-..........|..-.........................|............/.......................\\....\\......-......
            ........|...\\..............|/.........|...../...............................\\../............../...............
            ...................-.............-.|......-................\\........-...............-....\\..../\\....|..\\......
            .............|............\\....................../..........\\..../\\...........................................
            ..............................|.............../..|......-......./........|..............................|..-.\\
            ..............-............/|...\\..\\./.\\............./.............-.......\\............./../............-....
            .........................................|.........../................................/..........|......|../..
            ......|.........\\..\\..............................|.....-\\.-.\\...........|...............-....-....\\..........
            ...........................\\\\....|......|..............|....-../.....\\|....|\\........../..-...................
            ...........-..../.|.......-|...-.......................|.......-.|..../........................\\/.............
            .....................-.............\\.............../............................................\\\\............
            ......-..........-.-..|...........|...........................\\...............................|...........|...
            ................--...\\........\\.................../................................../............-...........
            ...............|../............/...../......./..\\............|..../-...........|..................\\.-.........
            |....................|-.\\...........\\...\\.............................................../................\\....
            \\.|................\\.\\...................\\....-./....\\......../...................-.....................-.....
            ...................................................|........../......\\.................|..\\.............../...
            ................/.............|......|...........-......................|...-.................../../..|.....-.
            .................|-..|.........................-............\\....-./......-......../............/.....|.......
            .....|.........-.....-.....-................/\\..........-........|...\\../..|.......-.........-...........-....
            ......................./.........../........|............\\............../........./.....\\....-/...............
            /........./.......|........|..../../........|............................\\........./-..-..............|.....|.
            ...........|.........-/........-...|/.............................-....|.....\\........................|.......
            .|..\\............/.-..........\\........-.............../.......\\...........\\..............\\../-..-|.|-........
            ..\\-|.......................\\.......\\.\\.....\\............-...../.......-....../.............................-.
            .-........................|........../.....................................-....\\.............................
            ..../.............-.................\\.|........................./............/.|..../../................\\-....
            ........../-................./........|......-../........|..-........./........./...........\\............\\....
            ......../.......................\\..-............-...................\\.....................\\/........\\...../...
            ....../....|..........-.....-/.................-...\\..........................................................
            /.........\\.............-....|.-..\\...../../......./|............................................./...../.....
            .......-..|............./.............../|....../..................\\....--..-./............./............./..-
            -...........|............/.........-..|..................../....................../...........|...\\\\..........
            /...-........../....................................|.......-.....|............|........././..\\...............
            ....................../....\\........|.....|....\\.......-............|\\....\\../.....--.....-.|.................
            ..../........-.....\\........\\.....\\...............|/..................|........./...-......|.................|
            .......................-.......//..................|...........|-....-..-...........-.......-....\\...../....\\.
            ..................-..../......./-............/.../...\\...\\../............\\\\.|.................................
            ........../.........|...-..........--....................../.......................-......../......\\..........
            ..|........../...........|............./...............|........-....\\.....\\...............-.....-....../.-...
            ...../.-..|\\...|...............................-...|....\\......|.............../.\\............\\............\\..
            ................\\/.....|..........\\.................../........\\./...................-........................
            .........\\...../..|............|...............|.-.....................\\....\\\\.|........../.......|...........
            ......................\\\\..../.....\\.....\\..........|....................\\.....................................
            .\\......./............-\\........../.........-...../....../....-..............-........................\\.....|.
            ...........\\.......-.....\\...\\/.......-..............-...........\\...................../.................\\....
            ............-.../.....|..............-.............|......|.......|..|.............../...|.....\\..............
            ................-..................\\..|............./.................\\....|..//.......|.../..................
            ........|.../...|....../...\\....................../....-...............-........|..........................|..
            ..|...../..............................-............|..........-........|.........../..|..|...................
            .....-..........-.....-.........................................|.........\\....-.....\\...........-..../...|...
            .-.-........../.................../...........-................|..-............../..-..-......................
            /-\\.........................-|.........-......|....|-...........-...................................-...\\.....
            ...\\.........|.............././...........|./.................-........\\.........\\.../|........./....\\\\.......
            ..|.................-......\\./....||.........\\....|..........-......|.................\\..........\\.....-../...
            ......../.|................................../...................|..\\\\.....--...................-..|..........
            .-........-......./...\\....|....../..........-./......./..|................\\/......../......../....../........
            ...-....../.|........./.............\\.....|../.-./.......................-....|.........\\......|/..........|..
            .....|................//.\\......|.\\/.................................|-...//........./...-.-............\\.....
            .............../...............\\..-....................-..../........../....-...............|-.......\\../...\\.
            ..|......\\........./../..................|....|................|\\.|....\\....-.|.......-../......||.-.|....\\./.
            ......|./.................\\............|.......................\\........-.....-...../..........\\......-.../..|
            .....|.\\....\\..............-......-./......./......................../............|..............|............
            ..............................\\..................../................|.................-......./.........../...
            .......-.....-..........\\..-...../.....\\.\\..............\\..\\....../.|.../...............-..................\\..
            .................|..................-.\\....................................\\.....-............................
            ...-.......|........-....-.......-....-..........-.-..\\.......................................\\.-.../......../
            .............-......\\................-.....-.....-/......./...\\\\../.................\\....-......./..-.....-|..
            ....\\.............|..-......./-......|............\\.........-........\\..../...\\.......|.|..-...\\..............
            .....................-....................................../.......\\....\\........................../\\..../\\..
            ........-............\\....\\./...........\\.-........./........./..\\.........................../....-..|......|.
            ..................../.........../.\\..............-............\\.........\\/...\\..................-.............
            .-...\\......\\.............................................................-.............\\.\\.-......|..........
            ................|..............|.-........../......\\.............|............./.|..-|..........|.............
            ...-........../....\\.../.../.........................................................../.........|.-......-...
            ..-....................-..........................|..../.......|........|../......./....../...-......../....-.
            ........\\............................-.......\\...../\\......|\\......-............./.........-..................
            ...../...../|..............|.-.....\\....-/...........\\...............\\.\\.......................-.-............
            ......\\.......\\.....................|................................\\.....\\..............................\\./.
            \\...............................-.............-../-............................./...............\\.|...........
            ......|......|................-|..-....\\/....\\...................-...........|...../....................\\\\....
            .............../..............-...|....................\\\\..............-....\\..........\\...............|....|.
            ...\\.-.......|..-/...................../|............\\......-.../...................../.......................
            ........../........./.........-....\\........................|./......./..............-...-....................
            ....-|-..............\\.......................\\.................|.......\\.........../..........-......./.......
            ..-...........................|\\.................\\................/\\................................../.|.....
            ......\\.................................|........\\./............-..............\\..........-.........../..-....
            ................-..........|.............|.....|....................-..........\\......|-\\....|...........-....
            ...|.........../\\............./.........\\........\\....................|...........................-.\\.........
            ../.|...................././......-..............-\\.....-........-.|...........\\......-./..-..................
            ......\\.......-..-..............................-................|.................../........................
            ..\\..-................/..-....-........./..........................-.../...........................-..........
            ............./-./.\\........./....-....................././.....|............-...............|./...........-|..
            ...................-...\\.....\\....................../............--....................../...........\\........
            .............-.....\\......\\./........................../.....................-...................\\.........-|.
            .....\\.........\\.../......................\\..................|.............................\\.|........./.....|
            .....\\.....\\./..............-............../..............\\.......|........../..//.-...-.\\......../.........\\.
            ..............\\...\\..........................\\...\\./.-...|.........|.......|..\\../.........................|..
            .\\..|......\\.\\\\.........../....../....../......./................../..|................/....|.................
            ./|............./...................................\\.-....\\..\\|./.-.............................\\.......\\....
            \\./........./.....\\............................/.../...\\....................|..............|.|............/|..
            ..\\............\\............\\.....-..........\\.......|../.|......./|......-...--...-..\\......|./.-......-.....
            ...............\\........|............\\|....|..\\.......-................/.........|....\\-.|.|..../-.......|....
            ...................|/.............|............/................|..|.\\.|.........|...\\........................
            ..................|../..../.......-................................-......................-.........|.......\\.
            ...............\\...../......\\./...../............/...........-..........\\....../.....\\.....\\......|...........
            ../........\\.....|..................\\......./.......-../.......\\.../.|....................................|...
            .|.|..\\....\\.|./.../........................./........-..\\.../.............\\.............................|....
            ..................|...\\................-..-/..................-...|./..................-..|...................""";
}