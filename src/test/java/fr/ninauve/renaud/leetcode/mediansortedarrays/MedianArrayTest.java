package fr.ninauve.renaud.leetcode.mediansortedarrays;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class MedianArrayTest {

    @Nested
    class GivenNotModified {
        @Test
        void odd() {
            MedianArray medianArray = MedianArray.fromSortedNumbers(new int[]{2, 4, 6, 8, 10});

            assertThat(medianArray.length()).isEqualTo(5);
            assertThat(medianArray.median()).isEqualTo(6);
        }

        @Test
        void even() {
            MedianArray medianArray = MedianArray.fromSortedNumbers(new int[]{2, 4, 6, 8, 10, 12});

            assertThat(medianArray.length()).isEqualTo(6);
            assertThat(medianArray.median()).isEqualTo(7);
        }
    }

    @Nested
    class GivenRemovedBellowMedian {
        @Test
        void odd() {
            MedianArray medianArray = MedianArray.fromSortedNumbers(new int[]{2, 4, 6, 8, 10});
            medianArray.removeBellowOrEqualToMedian();

            assertThat(medianArray.length()).isEqualTo(2);
            assertThat(medianArray.median()).isEqualTo(9);
        }

        @Test
        void even() {
            MedianArray medianArray = MedianArray.fromSortedNumbers(new int[]{2, 4, 6, 8, 10, 12});
            medianArray.removeBellowOrEqualToMedian();

            assertThat(medianArray.length()).isEqualTo(3);
            assertThat(medianArray.median()).isEqualTo(10);
        }
    }

    @Nested
    class GivenRemovedOverMedian {
        @Test
        void odd() {
            MedianArray medianArray = MedianArray.fromSortedNumbers(new int[]{2, 4, 6, 8, 10});
            medianArray.removeOverOrEqualToMedian();

            assertThat(medianArray.length()).isEqualTo(2);
            assertThat(medianArray.median()).isEqualTo(3);
        }

        @Test
        void even() {
            MedianArray medianArray = MedianArray.fromSortedNumbers(new int[]{2, 4, 6, 8, 10, 12});
            medianArray.removeOverOrEqualToMedian();

            assertThat(medianArray.length()).isEqualTo(3);
            assertThat(medianArray.median()).isEqualTo(4);
        }
    }

    @Nested
    class GivenRemovedBellowMedianTwice {
        @Test
        void odd() {
            MedianArray medianArray = MedianArray.fromSortedNumbers(new int[]{2, 4, 6, 8, 10, 12, 14, 16, 18});
            medianArray.removeBellowOrEqualToMedian();
            medianArray.removeBellowOrEqualToMedian();

            assertThat(medianArray.length()).isEqualTo(2);
            assertThat(medianArray.median()).isEqualTo(17);
        }

        @Test
        void even() {
            MedianArray medianArray = MedianArray.fromSortedNumbers(new int[]{2, 4, 6, 8, 10, 12, 14, 16, 18, 20});
            medianArray.removeBellowOrEqualToMedian();
            medianArray.removeBellowOrEqualToMedian();

            assertThat(medianArray.length()).isEqualTo(2);
            assertThat(medianArray.median()).isEqualTo(19);
        }
    }

    @Nested
    class GivenRemoveddOverMedianTwice {
        @Test
        void odd() {
            MedianArray medianArray = MedianArray.fromSortedNumbers(new int[]{2, 4, 6, 8, 10, 12, 14, 16, 18});
            medianArray.removeOverOrEqualToMedian();
            medianArray.removeOverOrEqualToMedian();

            assertThat(medianArray.length()).isEqualTo(2);
            assertThat(medianArray.median()).isEqualTo(3);
        }

        @Test
        void even() {
            MedianArray medianArray = MedianArray.fromSortedNumbers(new int[]{2, 4, 6, 8, 10, 12, 14, 16, 18, 20});
            medianArray.removeOverOrEqualToMedian();
            medianArray.removeOverOrEqualToMedian();

            assertThat(medianArray.length()).isEqualTo(2);
            assertThat(medianArray.median()).isEqualTo(3);
        }
    }

    @Nested
    class GivenRemovedBellowThenOverMedian {
        @Test
        void odd() {
            MedianArray medianArray = MedianArray.fromSortedNumbers(new int[]{2, 4, 6, 8, 10, 12, 14, 16, 18});
            medianArray.removeBellowOrEqualToMedian();
            medianArray.removeOverOrEqualToMedian();

            assertThat(medianArray.length()).isEqualTo(2);
            assertThat(medianArray.median()).isEqualTo(13);
        }

        @Test
        void even() {
            MedianArray medianArray = MedianArray.fromSortedNumbers(new int[]{2, 4, 6, 8, 10, 12, 14, 16, 18, 20});
            medianArray.removeBellowOrEqualToMedian();
            medianArray.removeOverOrEqualToMedian();

            assertThat(medianArray.length()).isEqualTo(2);
            assertThat(medianArray.median()).isEqualTo(13);
        }
    }
}