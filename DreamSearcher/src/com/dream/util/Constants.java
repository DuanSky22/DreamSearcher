package com.dream.util;

/**
 * Some useful constants.
 **/

public final class Constants {
    private Constants() {
    } // can't construct

    /** The value of System.getProperty("java.version"). **/
    public static final String JAVA_VERSION = System
	    .getProperty("java.version");
    /** True iff this is Java version 1.1. */
    public static final boolean JAVA_1_1 = JAVA_VERSION.startsWith("1.1.");
    /** True iff this is Java version 1.2. */
    public static final boolean JAVA_1_2 = JAVA_VERSION.startsWith("1.2.");
    /** True iff this is Java version 1.3. */
    public static final boolean JAVA_1_3 = JAVA_VERSION.startsWith("1.3.");

    /** The value of System.getProperty("os.name"). **/
    public static final String OS_NAME = System.getProperty("os.name");
    /** True iff running on Linux. */
    public static final boolean LINUX = OS_NAME.startsWith("Linux");
    /** True iff running on Windows. */
    public static final boolean WINDOWS = OS_NAME.startsWith("Windows");
    /** True iff running on SunOS. */
    public static final boolean SUN_OS = OS_NAME.startsWith("SunOS");

    public static final String OS_ARCH = System.getProperty("os.arch");
    public static final String OS_VERSION = System.getProperty("os.version");
    public static final String JAVA_VENDOR = System.getProperty("java.vendor");

    /** Segment file suffixes. */
    public static final String SEGMENT_FILE_PREFFIX = "segment";
    public static final String SEGMENT_FILE_SUFFIX = ".gen";
    public static final String SEGMENT_TEMPORARY_NAME = "temp";
    /** Dictionary file suffixes. */
    public static final String TERM_INFOS_FILE_SUFFIX = ".tis";
    public static final String TERM_INFOS_INDEX_FILE_SUFFIX = ".tii";
    /** Posting list file suffixes. */
    public static final String FREQUENT_FILE_SUFFIX = ".frq";
    public static final String POSITION_FILE_SUFFIX = ".prx";
    /** Field data file suffixes. */
    public static final String FIELD_META_FILE_SUFFIX = ".fnm";
    public static final String FIELD_INDEX_FILE_SUFFIX = ".fdx";
    public static final String FIELD_DATA_FILE_SUFFIX = ".fdt";

    // NOTE: this logic may not be correct; if you know of a
    // more reliable approach please raise it on java-dev!
    public static final boolean JRE_IS_64BIT;
    static {
	String x = System.getProperty("sun.arch.data.model");
	if (x != null) {
	    JRE_IS_64BIT = x.indexOf("64") != -1;
	} else {
	    if (OS_ARCH != null && OS_ARCH.indexOf("64") != -1) {
		JRE_IS_64BIT = true;
	    } else {
		JRE_IS_64BIT = false;
	    }
	}
    }

    // this method prevents inlining the final version constant in compiled
    // classes,
    // see: http://www.javaworld.com/community/node/3400
    private static String ident(final String s) {
	return s.toString();
    }
}
