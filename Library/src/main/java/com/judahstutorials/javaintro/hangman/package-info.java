/**
 * This package contains the implementation of a Hangman game.
 * I'm not publishing the code for it,
 * but I hope those of my students who choose
 * to pursue the project
 * can glean some insight into how to proceed
 * by reading these notes.
 * <h2>Preliminaries</h2>
 * <div style="float:right; vertical-align:top;">
 *      <img 
 *          src="doc-files/UnicodeHangman.png" 
 *          alt="Unicode Hangman" 
 *          width="25%" height="25%"
 *          style="vertical-align: top;"
 *      >
 *      <img 
 *          src="doc-files/GraphicHangman.png" 
 *          alt="Graphic Hangman" 
 *          width="25%" height="25%"
 *          style="vertical-align: top;"
 *      >
 * </div>
 * <ol>
 *      <li>
 *          The introduction to this project
 *          is found on the Judah's Tutorials web site,
 *          <a href="https://judahstutorials.com/Classes/JavaClass/recommended_projects/index.html">
 *              Introduction to Java Programming:
                Recommended Projects.
 *          </a>
 *      </li>
 *      <li>
 *          The introduction suggests three possible implementation
 *          for generating the artwork.
 *          Two of these use Unicode characters
 *          to approximate the image of the hanged man,
 *          and the other uses Java's graphics libraries.
 *          The implementation chosen for this project
 *          uses Unicode characters
 *          with embedded escape codes
 *          for cursor positioning.
 *      </li>
 *      <li>
 *          I won't describe the rules of the Hangman game here.
 *          You can find the instructions in
 *          <a href="https://www.google.com/search?q=wikipedia+hangman&rlz=1C1CHBD_enUS1190US1190&sca_esv=71ccb7caa41f84e2&sxsrf=AE3TifPN22y8nJLJNj7k8OoQhfO7LFYCCg%3A1766662820589&ei=pCJNacjYI-qLxc8Pt6WC-AY&ved=0ahUKEwjIuu_u09iRAxXqRfEDHbeSAG8Q4dUDCBE&uact=5&oq=wikipedia+hangman&gs_lp=Egxnd3Mtd2l6LXNlcnAiEXdpa2lwZWRpYSBoYW5nbWFuMgUQABiABDIGEAAYFhgeMgsQABgWGMcDGAoYHjIGEAAYFhgeMgYQABgWGB4yBhAAGBYYHjIGEAAYFhgeMgYQABgWGB4yBhAAGBYYHjIGEAAYFhgeSL5KUKILWL9DcAF4AZABAJgB6wGgAcwQqgEFMC45LjO4AQPIAQD4AQGYAg2gApwRwgIKEAAYsAMY1gQYR8ICChAjGIAEGCcYigXCAgQQIxgnwgILEAAYgAQYkQIYigXCAgoQABiABBhDGIoFwgILEC4YgAQY0QMYxwHCAgUQLhiABMICEBAuGIAEGNEDGEMYxwEYigXCAg0QABiABBhDGMkDGIoFwgIIEAAYgAQYkgPCAgoQLhiABBhDGIoFwgIZEC4YgAQYQxiKBRiXBRjcBBjeBBjgBNgBAZgDAIgGAZAGCLoGBggBEAEYFJIHBTEuOS4zoAfGX7IHBTAuOS4zuAeREcIHBDItMTPIBzmACAA&sclient=gws-wiz-serp">
 *              this Wikipedia article.
 *          </a>
 *      </li>
 *      <li>
 *          For random selection of possible words/phrases
 *          I started with the suggestions on the
 *          <a href="https://heartshangman.com/words/">Hearts Hangman</a>
 *          web page.
 *      </li>
 * </ol>
 * 
 * <h2>Strategies</h2>
 * <p>
 * Following are some of the strategies I used
 * in implementing the Hangman game.
 * <h3>Selecting the Word to Guess</h3>
 * <p>
 * I start the game
 * by asking the player (or another operator)
 * to select a word category, 
 * for example,
 * <em>hard, easy, funny, kids,</em> etc.
 * Each category is associated with multiple words,
 * one of which is chosen at random.
 * Instead of choosing a category,
 * the operator is offered the option 
 * of specifying their own word.
 * The words associated with a category
 * are stored in separate files
 * which are located in 
 * the project <em>resources</em> folder
 * <a href = "#footnote-1"><sup>1</sup></a>.
 * If you don't want to mess around with resource files,
 * you can store the files in the root of your project,
 * or hard-code them in a class file.
 * To present the choices to the operator
 * I use <em>JOptionPane.showOptionDialog.</em>
 * The result is kind of ugly,
 * but I don't expect my students
 * to be able to implement their own dialogs
 * <a href = "#footnote-2"><sup>2</sup></a>.
 * 
 * <h3>Lots of Little Classes</h3>
 * <p>
 * It would not surprise me to find
 * a Hangman game implemented in a single class.
 * For my implementation I used six:
 * four main classes
 * and two utility classes.
 * There are a lot of reasons
 * for choosing an implementation strategy
 * that uses a lot of little class,
 * for example:
 * <ul>
 *      <li>
 *          <strong>Focus</strong><br>
 *          When a class's responsibilities
 *          are restricted to a limited number of tasks
 *          (typically <em>one</em>)
 *          it's a lot easier to focus
 *          on those tasks, and
 *          <u>
 *              prevent the logic of one task
 *              from becoming interleaved
 *              with the logic of another
 *              (see also <em>spaghettification</em>).
 *          </u>
 *      </li>
 *      <li>
 *          <strong>Reuse</strong><br>
 *          You'd be surprised
 *          (well, maybe you wouldn't be surprised)
 *          how often you write a hundred
 *          (or five hundred or five thousand)
 *          lines of code and then discover
 *          that the code would be perfect
 *          for a task performed elsewhere.
 *          The correct response to this
 *          is to move the code
 *          to its own class
 *          (or a utility class)
 *          where it can be used anywhere you like
 *          (see, for example, the 
 *          <a href="#ImagePart">ImagePart</a>
 *          class below).
 *      </li>
 *      <li>
 *          <strong>Informal Testing</strong><br>
 *          Supposed your concentrating on the artwork
 *          for your hanged man.
 *          If your artwork logic
 *          is interleaved with the rest of your code,
 *          and your trying to get 
 *          the details of the right arm correct,
 *          you might have to do something like this:
 *          <ul>
 *              <li>Start the application;</li>
 *              <li>Select a word to guess;</li>
 *              <li>Make five, consecutive incorrect guesses;</li>
 *              <li>See that you don't like how the right arm is drawn;</li>
 *              <li>Change a 3 to a 4 in your code;<li> and
 *              <li>Start again from the top. (And again, and again.)</li>
 *          </ul>
 *          If you restrict your drawing to a single class
 *          you can put a short main method in that class
 *          and quickly see the result of editing your code.
 *      </li>
 * </ul>
 * <p>
 * The classes in my Hangman implementation
 * consist of the following.
 * </p>
 * <h4>The WordSelector Class</h4>
 * <p>
 * This class is responsible exclusively for
 * <h4 id="ImagePart">The ImagePart class</h4>
 * <p>
 * This class
 * 
 * <div style="border-top: 2px solid black; font-family: sans-serif; font-size: 75%;">
 *      <p id="footnote-1" style="margin-bottom: 0;">
 *      <sup>1</sup>
 *      For more information about using resource files see
 *      <a href="https://judahstutorials.com/cartesian-plane-lesson-7-property-management-page-1#loading-the-user-properties-file">
 *           Loading the User Properties File
 *      </a>
 *      <p id="footnote-2">
 *      <sup>2</sup>
 *      For more information about custom dialogs, see
 *      <a href="https://www.geeksforgeeks.org/java/java-swing-jdialog-examples/">
 *           JDialog with Examples
 *      </a>
 *      on the GeeksforGeeks website.
 * </div>
 */
package com.judahstutorials.javaintro.hangman;

