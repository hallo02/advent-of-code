package ch.hallo02.adventofcode.year2023.day7;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class Day7_1 {

    // a hand has 5 cards
    // too low 500500
    // too high 249299265
    // win 249204891

    static String miniPuzzle = """
            32T3K 765
            T55J5 684
            KK677 28
            KTJJT 220
            QQQJA 483
            QQQAA 700""";

    public static void main(String... args) {

        List<Hand> hands = puzzle.lines()
                .map(Day7_1::toHand)
                .sorted((h1, h2) -> getComparator(h1, h2))
                .toList();

        hands.forEach(System.out::println);

        long result = 0;
        int rank = 1;
        for(Hand h: hands){
            result += rank * h.bid;
            rank++;
        }
        System.out.println(result);

    }

    static Hand toHand(String l) {
        String rawInput = l.split(" ")[0].trim();
        Long bid = Long.valueOf(l.split(" ")[1].trim());
        return new Hand(rawInput, bid);
    }

    static int getComparator(Hand h1, Hand h2) {
        var r1 =  h1.handType.rank - h2.handType.rank;

        if(r1 != 0){
            return r1;
        }

        int r2 = 0;
        int pos = 0;
        while(r2 == 0){
            r2 = getStrength(h1.rawInput.charAt(pos)) - getStrength(h2.rawInput.charAt(pos));
            pos++;
        }
        return r2;
    }

    static int getStrength(Character c){
        switch (c) {
            case '2': return 2;
            case '3': return 3;
            case '4': return 4;
            case '5': return 5;
            case '6': return 6;
            case '7': return 7;
            case '8': return 8;
            case '9': return 9;
            case 'T': return 10;
            case 'J': return 11;
            case 'Q': return 12;
            case 'K': return 13;
            case 'A': return 14;
            default: throw new RuntimeException();
        }
    }


    static class Hand {
        String rawInput;
        HandType handType;
        Long bid;

        Hand(String rawInput, Long bid) {
            this.rawInput = rawInput;
            this.handType = getHandType(this.rawInput);
            this.bid = bid;
        }

        @Override
        public String toString() {
            return rawInput + "(" + handType + ")";
        }
    }

    static HandType getHandType(String rawInput) {
        // FIVE KIND
        if (isFiveKind(rawInput)) {
            return HandType.FIVEKIND;
        } else if (isFourKind(rawInput)) {
            return HandType.FOURKIND;
        } else if (isFullHouse(rawInput)) {
            return HandType.FULLHOUSE;
        } else if (isThreeKind(rawInput)) {
            return HandType.THREEKIND;
        } else if (isTwoPair(rawInput)) {
            return HandType.TWOPAIR;
        } else if (isPair(rawInput)) {
            return HandType.PAIR;
        } else {
            return HandType.HIGHCARD;
        }
    }

    static boolean isFiveKind(String rawInput) {
        return rawInput.chars().distinct().count() == 1;
    }

    static boolean isFourKind(String rawInput) {
        var map = new HashMap<Character, Integer>();

        rawInput.chars().forEach(c -> {
            var character = Character.valueOf((char) c);
            if (map.get(character) == null) {
                map.put(character, 1);
            } else {
                map.put(character, map.get(character) + 1);
            }
        });

        var values = map.values().stream().sorted().toList();

        return values.get(values.size() - 1) == 4;

    }

    static boolean isFullHouse(String rawInput) {
        var map = new HashMap<Character, Integer>();

        rawInput.chars().forEach(c -> {
            var character = Character.valueOf((char) c);
            if (map.get(character) == null) {
                map.put(character, 1);
            } else {
                map.put(character, map.get(character) + 1);
            }
        });

        var values = map.values().stream().sorted().toList();

        return values.get(values.size() - 1) == 3 && values.get(values.size() - 2) == 2;
    }

    static boolean isThreeKind(String rawInput) {
        var map = new HashMap<Character, Integer>();

        rawInput.chars().forEach(c -> {
            var character = Character.valueOf((char) c);
            if (map.get(character) == null) {
                map.put(character, 1);
            } else {
                map.put(character, map.get(character) + 1);
            }
        });

        var values = map.values().stream().sorted().toList();

        return values.get(values.size() - 1) == 3;
    }

    static boolean isTwoPair(String rawInput) {

        var map = new HashMap<Character, Integer>();

        rawInput.chars().forEach(c -> {
            var character = Character.valueOf((char) c);
            if (map.get(character) == null) {
                map.put(character, 1);
            } else {
                map.put(character, map.get(character) + 1);
            }
        });

        var values = map.values().stream().sorted().toList();

        return values.get(1) == 2 && values.get(2) == 2;

    }

    static boolean isPair(String rawInput) {

        var map = new HashMap<Character, Integer>();

        rawInput.chars().forEach(c -> {
            var character = Character.valueOf((char) c);
            if (map.get(character) == null) {
                map.put(character, 1);
            } else {
                map.put(character, map.get(character) + 1);
            }
        });

        var values = map.values().stream().sorted().toList();

        return values.get(values.size() - 1) == 2;

    }


    enum HandType {
        FIVEKIND(7), FOURKIND(6), FULLHOUSE(5), THREEKIND(4), TWOPAIR(3), PAIR(2), HIGHCARD(1);

        int rank;

        HandType(int rank) {
            this.rank = rank;
        }
    }

    static String puzzle = """
            JJJJ8 619
            Q4J94 152
            77587 277
            7333J 651
            QQQQ2 419
            72KA3 851
            555Q2 806
            37QTT 72
            39446 597
            KK99T 453
            T5522 247
            8TK48 109
            46J82 146
            444A7 788
            Q9TQJ 116
            3A9AA 529
            5AAAJ 63
            T9522 668
            ATJTJ 879
            7TATT 11
            88686 885
            5QJ55 782
            72K77 576
            KQ48J 352
            JJ488 704
            3K356 12
            JQJAQ 201
            26272 373
            88JJ2 855
            35333 167
            755Q4 465
            5J6T5 136
            JTA23 477
            J8488 252
            55556 417
            55T8T 193
            22782 148
            2372J 811
            J4K72 114
            9Q4KK 303
            4J2J2 388
            6AJ9Q 965
            45335 952
            982T3 184
            55Q5Q 762
            TK572 972
            T5TT5 250
            2Q8Q8 119
            AA436 353
            6KKK5 501
            399J9 561
            T8AA3 406
            JKAAK 482
            55585 695
            KJTK7 559
            27JJT 690
            4QQQ4 990
            J32J3 24
            5J545 863
            558QQ 920
            49949 187
            JQQK9 179
            T29JK 605
            699J6 445
            KK7QJ 284
            647AJ 509
            KK8K3 893
            7KK79 174
            J8J55 523
            6T3AQ 410
            44349 296
            JJJJJ 158
            99998 570
            5TKQ6 266
            9933T 300
            K665K 724
            QTTTQ 807
            7Q5Q5 375
            Q6666 966
            TQ35K 825
            T5QQ5 18
            KQ794 802
            K42KT 240
            QKKJJ 97
            77647 504
            87J84 443
            KATTK 666
            52J88 889
            66JKJ 977
            6AQ94 84
            36A78 514
            AA67A 286
            74Q34 490
            QQ8QQ 79
            3K555 323
            Q3JKT 338
            2A222 448
            935JJ 798
            43QTJ 226
            5TA7K 162
            553QQ 420
            QKKQ9 204
            9399K 565
            8T66T 638
            4444Q 83
            J2223 173
            4T454 639
            4K3K4 454
            Q2255 955
            965JJ 658
            A48K3 409
            JJA22 728
            42242 677
            AKT6J 155
            77774 838
            82AAA 894
            22A6K 775
            59377 235
            6K86J 451
            26AT6 671
            8388J 47
            JJ293 449
            39333 500
            62222 456
            5K55Q 36
            KKK77 773
            QQ739 287
            67686 588
            53329 341
            TQTTT 213
            44224 796
            AAAJA 222
            6KK22 92
            AJK2A 627
            5T858 739
            J6579 462
            T3933 717
            3AQ82 380
            Q564Q 898
            8QQQ7 470
            93T29 317
            64K67 867
            JTTTT 299
            4499J 714
            6QQ76 435
            AJ979 897
            66KTK 594
            2AA84 178
            8858K 533
            KKKKJ 689
            99393 906
            2A777 711
            33Q78 540
            55566 364
            2AA3A 513
            5KJKK 98
            Q79T9 809
            AKKKK 34
            5932K 349
            88858 198
            52222 307
            944T7 126
            T36Q7 472
            T4444 792
            TTKJT 828
            35652 580
            ATQ79 94
            ATJTA 518
            3JT52 656
            2TT23 438
            9K497 630
            AQ735 569
            95997 175
            27248 874
            3AKJK 693
            94979 709
            2QQT2 137
            4A598 253
            33JQ2 520
            8A747 457
            K222A 720
            89A99 87
            T8TTA 706
            Q489A 322
            J4TT4 319
            T5JTK 57
            T7T97 280
            A7A7A 471
            9K583 91
            77887 909
            5JJ55 629
            AJ434 901
            JJ7A9 166
            Q99J9 577
            44T4T 688
            T8J8K 759
            5AJ5A 149
            95599 890
            3Q662 781
            JAJ5A 940
            TTJ3J 676
            88976 203
            4AA4Q 985
            95T53 956
            K3333 595
            52555 544
            AK942 684
            2T548 415
            K66J7 407
            KT7T2 103
            97973 14
            43343 708
            55Q53 422
            A835K 616
            TT589 236
            6322K 186
            A4K4A 866
            2K563 861
            5J255 591
            Q9Q98 450
            T7796 305
            9K229 258
            9A27J 993
            5QKQJ 224
            T5J5T 161
            3TA3K 464
            558A8 183
            Q333Q 48
            A5A55 771
            34437 182
            8KKKK 95
            J5696 291
            66K88 914
            33489 661
            KK2TT 498
            59999 877
            7TTTT 492
            5J52K 531
            398K6 783
            99A9T 49
            667TT 86
            AAKK6 928
            2626J 346
            3J675 37
            T8488 268
            KK222 763
            76666 749
            47457 138
            QTTT6 211
            TTT8T 754
            37754 652
            2QK4K 770
            3A3AA 988
            KJ35A 779
            434AA 962
            44JJ4 519
            K2QQQ 133
            JJ77J 662
            89899 292
            AA9AA 10
            3J3J3 329
            477JJ 841
            39Q33 609
            8A26T 431
            TK63J 641
            57595 880
            KQ7QJ 123
            QJJK5 665
            88AKA 566
            73Q67 265
            7673T 904
            Q7Q2A 937
            9223Q 991
            T45A7 386
            96J6A 220
            QK748 859
            QQAQ8 172
            222K2 100
            QQJ7Q 822
            9AK3A 873
            2766A 647
            K4432 402
            TKTTK 77
            24423 915
            J5423 853
            32243 463
            Q3QQQ 15
            676T6 368
            54555 378
            AJ7A6 192
            9J3JA 139
            2TT3T 393
            QT33A 503
            56684 120
            Q56Q2 786
            K32AT 943
            384A7 780
            44Q4Q 117
            6AJ54 214
            9J9K9 189
            7TKKT 808
            34J8J 360
            AAATA 755
            JQJTA 705
            T9T94 387
            4J445 718
            Q8Q8Q 6
            T652J 745
            Q9272 372
            T9924 936
            3J352 691
            577T5 827
            QQ344 596
            29TTJ 310
            2332T 752
            66J66 217
            999JJ 261
            Q968T 857
            5233K 165
            3K3KA 489
            33T3J 964
            KT3TT 507
            T88TT 244
            55595 350
            6944J 791
            224AT 887
            3447J 30
            T2K86 169
            Q23Q3 556
            KK88K 602
            4AAA6 534
            ATA85 626
            8J3KA 699
            KKQKK 931
            4TTTQ 328
            A3684 923
            62AA2 776
            8464K 121
            846K6 944
            6J69J 610
            KK55J 769
            7TKQ2 358
            TQ4TK 93
            JK4A4 698
            842J8 971
            JJ222 129
            8J32J 233
            278K2 674
            68Q8J 727
            A663T 318
            56A5A 64
            7775Q 180
            K568Q 846
            9K8AA 279
            AJTTK 196
            JA254 151
            5Q2A3 913
            A8788 816
            48729 267
            QKQT4 572
            Q4949 953
            43KK3 125
            83676 2
            J9959 76
            TJTT3 38
            472T5 948
            5T679 511
            JJ52A 293
            7TT99 649
            47467 176
            J3J5K 844
            8KJKK 982
            AAJJA 961
            6K633 589
            45Q96 208
            6QQQJ 888
            46464 701
            T33JT 52
            625J9 336
            K84T2 20
            54447 246
            T8AT8 118
            827A6 795
            99967 274
            TT44T 61
            AA2KK 784
            Q2AAT 276
            KA9AA 478
            3QJQK 452
            A97JK 551
            K4774 746
            QKKTT 371
            63834 369
            QQ4QQ 163
            54757 835
            7Q436 919
            3AA33 560
            8Q288 546
            99K99 74
            QJ9QA 67
            48888 404
            4KQA3 850
            36872 930
            359T4 426
            T6578 315
            626T6 772
            K79A9 599
            4A9JA 653
            366J6 45
            2T8J7 628
            TTTT3 967
            A426J 202
            QA6T2 760
            7775A 73
            488K7 16
            KTTTT 644
            84JA9 484
            57775 232
            KTQ8T 7
            8JK88 681
            5A5J8 733
            66A66 228
            K28A4 736
            59647 682
            4Q6A5 663
            62226 578
            5T489 26
            6J65T 548
            24725 9
            K35TJ 314
            26A68 455
            JJTTT 907
            AJ285 899
            JQJQJ 927
            QQTJQ 554
            AT235 141
            TJK9J 35
            9666Q 499
            66A76 999
            86K94 947
            495AT 632
            TK385 403
            993KK 530
            38366 625
            4J744 881
            AA777 694
            84994 248
            46Q2A 39
            TT6TJ 751
            A55AA 741
            2Q545 946
            QT238 959
            22A3A 845
            6T9T7 710
            KKJQK 856
            888A8 722
            3899J 239
            969A9 365
            A88A8 62
            A77J7 945
            Q6222 288
            2QQ62 761
            K22TQ 270
            2JJJJ 421
            KTTKJ 678
            J32A7 787
            58688 8
            55499 814
            KK7KK 425
            9Q97K 82
            53353 804
            5KQJT 590
            67677 963
            K2A9K 747
            A424A 926
            AJ3T3 891
            75A29 132
            5994T 502
            J8J5K 399
            44JQQ 437
            6AKQ9 670
            JA376 905
            KAKQA 110
            464JA 908
            88228 719
            37773 558
            555K5 27
            KTT38 902
            7J77J 821
            T888T 13
            J9K4T 497
            7KJK5 738
            7AKQ2 969
            TQQQ5 785
            5TAT2 849
            JT5A5 143
            QAAAQ 496
            QQKKK 231
            T77K7 488
            77TTQ 461
            TAQ69 535
            43T47 933
            8K3KJ 212
            768QQ 573
            A75A5 643
            88JT4 130
            J2QQ2 444
            KT788 515
            22922 75
            73TJ5 379
            75555 25
            27666 408
            J4J3T 41
            94Q57 525
            A85A5 273
            66386 526
            J98KK 618
            A6A64 234
            2KK2K 147
            3KKKK 636
            88JJ8 495
            T5T55 58
            Q959J 734
            9J292 508
            686J8 376
            A5862 309
            J9T3T 875
            9Q99Q 22
            T773Q 970
            Q3QT6 424
            4455A 466
            77443 469
            49999 405
            Q65AA 655
            TA576 840
            A9999 96
            AA9J9 256
            5999K 941
            28338 423
            797KT 581
            5K55K 819
            7A3KK 613
            7Q77K 434
            KK686 102
            76755 257
            Q4429 185
            922K2 712
            AA4AA 842
            A2AJA 326
            78A3T 40
            T2Q2T 483
            47A7A 249
            54558 703
            6972Q 522
            AJ689 586
            Q3Q5J 669
            5KK65 262
            2A2AA 847
            A62Q7 737
            2KT2K 645
            4K4TJ 929
            AK993 794
            48576 839
            T4683 604
            Q3556 686
            67Q2K 871
            AA665 370
            Q66Q9 925
            J77Q7 660
            TJAA7 374
            TT6TA 574
            AJJ4A 414
            3TK28 337
            95AKT 864
            777J9 541
            67979 171
            8888J 829
            5QJQ5 345
            884J4 225
            JQK6T 106
            6K6KJ 367
            333A6 89
            59939 506
            4J525 642
            6A765 624
            A282J 852
            2QJQQ 107
            K8822 70
            67KQA 316
            7J8TQ 111
            5555J 837
            42TQ7 112
            J2J55 886
            2Q9QJ 942
            ATATT 778
            7J676 304
            5K8T7 269
            9KJA2 275
            36TJ3 324
            QJ954 976
            58353 960
            59436 42
            AA444 188
            3A333 834
            JK695 973
            39383 394
            A5566 17
            55533 975
            49448 742
            A8KJK 486
            QQQJ9 524
            73T5Q 65
            8T265 441
            44774 206
            8J329 633
            5555Q 823
            76962 195
            2QQTT 648
            KAQ4Q 882
            6K9A3 312
            62JT8 860
            AKAA5 381
            8888K 216
            66622 4
            76JTJ 884
            KKK2K 343
            2A345 685
            J922A 440
            2AAAA 487
            K49K5 391
            74QJ2 984
            4777Q 750
            76676 553
            5A829 995
            32K22 584
            J89Q5 181
            66663 646
            J4857 335
            66462 623
            36A63 593
            T2Q72 620
            44244 330
            QJ4A7 512
            8J8T8 227
            7ATA7 290
            3A993 325
            QQ2Q2 229
            KKQ5Q 134
            KA658 389
            QQ94Q 467
            5636J 592
            49494 59
            27272 460
            T8T98 536
            T2TTJ 215
            TJQ4Q 68
            5J5JJ 321
            48A79 910
            8465J 954
            K734A 71
            9QJ33 101
            AQAJQ 177
            777J7 401
            K9TTT 1
            8A394 532
            7TTJT 748
            AQT7J 355
            J547T 735
            8882K 398
            33773 916
            J7477 294
            5QQQ5 935
            9K9T9 50
            45554 606
            J44JA 427
            6A494 731
            KQK5T 521
            77447 331
            43J33 543
            8592Q 468
            Q62TJ 430
            99AA9 687
            J26J6 283
            J7697 587
            44J44 716
            7K7K7 90
            9KJ49 723
            72724 475
            AA77J 831
            33363 621
            Q3QJQ 306
            66QQQ 744
            9AT23 207
            22733 713
            TA67T 883
            4447K 892
            9JA99 598
            45646 69
            58225 537
            TJTT9 797
            JQJQQ 978
            66T6A 219
            TT6TT 124
            J484Q 344
            TQ32A 836
            6J6J6 29
            97J9J 979
            6J266 622
            AJ96T 218
            72KKK 298
            2A7A2 243
            TJJ7T 758
            8K682 974
            25QKA 411
            QQQ24 862
            56667 245
            56QQQ 43
            477J4 481
            Q7A5J 555
            45T54 545
            TAQQQ 989
            7AAAA 766
            6Q852 951
            84Q39 458
            JKJ42 730
            AT376 313
            2J427 108
            9TQJ6 429
            444AJ 150
            7TQJT 479
            QQJQQ 127
            2K534 547
            A888Q 272
            JAA65 66
            88585 600
            Q92Q9 528
            7TTA7 725
            3JQT8 160
            444A3 800
            J5558 878
            JQ5K6 608
            827Q3 122
            J9999 607
            KJQ69 140
            K368J 99
            5T777 721
            K6665 582
            2666A 683
            6668J 817
            79779 221
            JQ9Q5 474
            J9996 692
            66J76 396
            53577 732
            JATAA 803
            3KJK6 447
            3K37K 439
            TQQT3 1000
            62A64 416
            78295 571
            33334 726
            J7999 896
            98988 493
            5AQ5J 153
            4QJ44 320
            4JQQQ 168
            55252 385
            33388 615
            29292 679
            2QAKK 327
            48848 702
            4J3T8 476
            QT332 869
            35899 53
            29A9A 308
            77722 777
            685T5 824
            2242J 238
            66QKJ 131
            6K38Q 397
            5K798 400
            A9JK6 793
            255A2 51
            33TTT 557
            6Q6Q6 311
            Q4687 957
            KK67K 820
            T56A3 432
            75A8J 200
            696J6 78
            94954 382
            2Q89J 356
            35A3A 56
            9J977 575
            A65K2 550
            J6367 32
            27222 194
            KAAKA 348
            AAAA3 395
            TJ7T7 715
            T3Q92 433
            AQ99Q 843
            79957 263
            9899K 986
            64Q26 801
            34A4Q 922
            J3838 567
            QQQ5Q 667
            JT732 347
            A8QAT 872
            43T7K 413
            4Q445 362
            34T44 491
            A33A7 932
            22T47 340
            25224 281
            K97Q2 485
            77577 949
            466J6 637
            88868 868
            22822 996
            3AAA4 302
            86J8J 997
            Q24Q2 983
            4224T 357
            T9254 826
            Q2777 980
            K5KKA 28
            K8K88 654
            89432 494
            333J3 538
            79T46 428
            25252 255
            TT8T9 998
            425AA 157
            29TAQ 145
            877K7 60
            558Q3 631
            62665 542
            Q5948 442
            683JQ 197
            49483 697
            3K35K 938
            8TT47 601
            44JAT 568
            T29Q4 33
            AKJ9K 264
            7AAA4 549
            T47A6 241
            27A33 3
            3AA3K 260
            6AK9K 516
            9AJ85 113
            K4454 767
            Q63JJ 764
            4AJK2 958
            66266 278
            74869 994
            8KTK8 768
            73A63 19
            A7285 813
            TA9J4 657
            JTQKT 209
            J7A55 88
            JAA9A 480
            77282 950
            98J88 359
            7K2K2 753
            44494 700
            K9J6T 918
            KJ74J 282
            QKQK4 640
            T6669 743
            A3QAQ 31
            74AAJ 611
            595J9 757
            5JA59 392
            78878 334
            T4TTT 799
            77678 21
            33KKK 832
            7787J 740
            KAKKA 170
            7KQ3Q 870
            KK9K3 858
            63JJ4 351
            T65J9 199
            37895 412
            9J9A8 833
            K3KJ5 765
            785T2 259
            Q3Q38 564
            JJQJA 446
            Q7JK3 527
            QKK86 634
            4653K 812
            3JK78 815
            J2255 242
            95A52 789
            93539 254
            5K6Q4 205
            444KK 142
            57K75 191
            3T887 154
            JA685 612
            TA9J8 104
            36847 517
            KJQQK 583
            22J2A 865
            66343 774
            44884 128
            779Q9 934
            T3476 790
            4A446 510
            A8AAA 384
            666AJ 390
            64347 921
            QJ8KA 418
            JJQ6T 563
            J4236 23
            AJJ37 339
            44448 271
            Q6K96 297
            57J55 115
            TTK8K 729
            666AA 377
            24662 251
            4TAAA 135
            83353 659
            2J429 354
            9K9K9 756
            AQQQQ 459
            45445 301
            A775J 912
            38333 237
            T3T88 680
            9Q2Q3 190
            268K3 164
            868Q7 987
            66466 44
            QJJKQ 818
            T48KQ 854
            526J6 363
            69666 295
            55KAA 895
            K75Q4 144
            TKKKA 332
            98699 650
            78828 848
            KT45K 617
            275TA 992
            J38QA 635
            TKA3K 579
            4Q4Q5 672
            7377T 614
            KT5J7 675
            QA8TJ 903
            2Q66Q 539
            44J46 917
            4K539 968
            3654Q 55
            A33J7 505
            23959 924
            T25JK 664
            33Q88 911
            Q6677 333
            9669K 289
            9782A 81
            66699 876
            6ATAK 552
            87772 223
            5T555 366
            9KK9K 900
            55666 673
            77KKT 105
            55358 156
            AA4AJ 342
            22TJ2 436
            8A448 361
            4KKK4 80
            84J7Q 805
            ATQTJ 473
            K92JQ 707
            222J2 981
            KQ7J7 603
            55JJA 46
            QKJAA 696
            842K5 562
            K58K8 5
            T9T5T 939
            TT956 230
            53555 810
            JK8K8 585
            J795A 85
            4T8T8 54
            QKK77 830
            KKKJJ 210
            9T9J9 383
            6Q464 285
            73257 159""";

}