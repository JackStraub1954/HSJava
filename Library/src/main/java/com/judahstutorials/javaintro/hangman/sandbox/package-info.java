/**
 * This package is part of the Hangman project.
 * It contains application and utility classes
 * for demonstrating concepts,
 * such as what an escape sequence is,
 * and illustrating techniques,
 * such as how to merge arrays representing
 * different parts of the hanged man artwork.
 * Running many of the applications
 * will require a command-line utility such as
 * <strong>
 *      Windows Terminal (wt.exe),
 *      terminal (macOS),
 *      quake,
 *      console,
 * </strong>
 * or
 * <strong>
 *      xterm.
 * </strong>
 * To run an application from the command line,
 * first make sure the latest version
 * of the javaintro-... jar file 
 * is in your classpath.
 * Then open a command line
 * and enter <em>java</em>
 * followed by the full class name
 * of the target application class.
 * For example,
 * to execute <em>EscDemo1</em> type:
 * <pre>java com.judahstutorials.javaintro.hangman.sandbox.EscSeqDemo1</pre>
 * <p>
 * Some of the classes in this package are:
 * </p>
 * <ul>
 *     <li>
 *          <strong>EscSeqDemo1</strong><br>
 *          A simple, "hello world" style application
 *          that introduces escape sequences.
 *     </li>
 *     <li>
 *          <strong>EscSeqDemo2</strong><br>
 *          Builds the Hangman artwork step by step, 
 *          pausing for input at each step, 
 *          just as the final Hangman application will.
 *     </li>
 *     <li>
 *          <strong>MergeDemo1</strong><br>
 *          Exercises the <em>ImagePart</em> class.
 *          It passes invalid data to an ImagePart object,
 *          and lets us examine the complicated error messages
 *          this class produces.
 *     </li>
 *     <li>
 *          <strong>WordSelectorDemo</strong><br>
 *          Exercises the <em>WordSelector</em> class,
 *          giving us confidence
 *          that our word-selection logic is working
 *          (see {@link com.judahstutorials.javaintro.hangman.WordSelector}).
 *     </li>
 *     <li>
 *          <strong>SimpleHangman</strong><br>
 *          This is a stripped-down implementation
 *          of a Hangman application.
 *          It allows us to follow the sequence of steps
 *          in a Hangman application
 *          without the complexity of 
 *          the artwork logic.
 *     </li>
 * </ul>
 */
package com.judahstutorials.javaintro.hangman.sandbox;
