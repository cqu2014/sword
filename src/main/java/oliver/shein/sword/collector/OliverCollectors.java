package oliver.shein.sword.collector;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * @Author Oliver Wang
 * @Description Oliver 实现的集合收集器
 * @Created by IntelliJ IDEA 2018.3.3
 * @Date Create at 2019/3/8
 */
public class OliverCollectors {

    /**
     * 默认构造函数
     */
    private OliverCollectors() {
    }

    /**
     * {@link java.util.stream.Collector} Simple implement
     *
     * @param <T>
     * @param <A>
     * @param <R>
     */
    static class OliverCollectorsImpl<T, A, R> implements Collector<T, A, R> {
        /**
         * 结果容器生成器
         */
        private final Supplier<A> supplier;

        /**
         * 累加器
         */
        private final BiConsumer<A, T> accumulator;

        /**
         * 分区结果合并器
         */
        private final BinaryOperator<A> combiner;

        /**
         * 结果转换器
         */
        private final Function<A, R> finisher;

        /**
         * 收集器特性
         */
        private final Set<Characteristics> characteristics;

        /**
         * 默认结果转化器
         *
         * @param <I>
         * @param <R>
         * @return
         */
        private static <I, R> Function<I, R> castingIdentity() {
            return i -> (R) i;
        }

        OliverCollectorsImpl(Supplier<A> supplier,
                             BiConsumer<A, T> accumulator,
                             BinaryOperator<A> combiner,
                             Function<A, R> finisher,
                             Set<Characteristics> characteristics) {
            this.supplier = supplier;
            this.accumulator = accumulator;
            this.combiner = combiner;
            this.finisher = finisher;
            this.characteristics = characteristics;
        }


        OliverCollectorsImpl(Supplier<A> supplier,
                             BiConsumer<A, T> accumulator,
                             BinaryOperator<A> combiner,
                             Set<Characteristics> characteristics) {
            this(supplier, accumulator, combiner, castingIdentity(), characteristics);
        }

        @Override
        public Supplier<A> supplier() {
            return this.supplier;
        }

        @Override
        public BiConsumer<A, T> accumulator() {
            return this.accumulator;
        }

        @Override
        public BinaryOperator<A> combiner() {
            return this.combiner;
        }

        @Override
        public Function<A, R> finisher() {
            return this.finisher;
        }

        @Override
        public Set<Characteristics> characteristics() {
            return this.characteristics;
        }
    }

    /**
     * 实现Oliver的toList方法
     *
     * @param <T>
     * @return
     */
    public static <T> Collector<T, ?, List<T>> toList() {
        return new OliverCollectorsImpl<>((Supplier<List<T>>) ArrayList::new,List::add,
                (left,right)->{left.addAll(right); return left;},
                Collector.Characteristics.IDENTITY_FINISH);

    }
}
