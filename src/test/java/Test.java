public class Test {

    private static final int COUNT_BITS = Integer.SIZE - 3;
    private static final int CAPACITY   = (1 << COUNT_BITS) - 1;


    private static final int RUNNING    = -1 << COUNT_BITS;
    private static final int SHUTDOWN   =  0 << COUNT_BITS;
    private static final int STOP       =  1 << COUNT_BITS;
    private static final int TIDYING    =  2 << COUNT_BITS;
    private static final int TERMINATED =  3 << COUNT_BITS;

    public static void main(String[] args) {

        testCtl();

//        int count = 0;
//        i:
//        for (;;) {
//            count = 0;
//            for (;;) {
//                count ++;
//                System.out.println("count = " + count);
//                if (count % 5 == 0) {
////                    break i; // 直接跳出循环；
//                    continue i; // 跳转到i处;
//                }
//            }
//        }

    }

    public static void testCtl() {

        System.out.println("   RUNNING: "+Integer.toBinaryString(RUNNING));
        System.out.println("  SHUTDOWN: "+Integer.toBinaryString(SHUTDOWN));
        System.out.println("      STOP: "+Integer.toBinaryString(STOP));
        System.out.println("   TIDYING: "+ Integer.toBinaryString(TIDYING));
        System.out.println("  CAPACITY: "+Integer.toBinaryString(CAPACITY));
        System.out.println(" ~CAPACITY: "+Integer.toBinaryString(~CAPACITY));

        System.out.println("-------------------------------------------------------------------");

        System.out.println("  CAPACITY: "+CAPACITY);
        System.out.println("   RUNNING: "+RUNNING);
        System.out.println("  SHUTDOWN: "+SHUTDOWN);
        System.out.println("      STOP: "+STOP);
        System.out.println("   TIDYING: "+TIDYING);
        System.out.println("TERMINATED: "+TERMINATED);

    }
}


