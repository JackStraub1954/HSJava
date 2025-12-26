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
 *          is found on Judah's Tutorials website,
 *          <a href="https://judahstutorials.com/Classes/JavaClass/recommended_projects/index.html">
 *              Introduction to Java Programming:
                Recommended Projects.
 *          </a>
 *      </li>
 *      <li>
 *          The introduction suggests three possible implementations
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
 * such as <em>hard, easy, funny, or kids</em>.
 * Each category is associated with multiple words,
 * and one is chosen at random.
 * Instead of choosing a category,
 * the operator can specify their own word.
 * The words associated with a category
 * are stored in separate files, located in 
 * the project <em>resources</em> folder
 * <a href = "#footnote-1"><sup>1</sup></a>.
 * If you don't want to mess around with resource files,
 * you can store the files in the root of your project
 * or hard-code the word lists in a class.
 * To present the choices to the operator,
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
 * For my implementation, I used six:
 * four main classes
 * and two utility classes.
 * There are a lot of reasons
 * for choosing an implementation strategy
 * that uses a lot of little classes,
 * for example:
 * <ul>
 *      <li>
 *          <strong>Focus</strong><br>
 *          When a class's responsibilities
 *          are restricted to a limited number of tasks
 *          (typically <em>one</em>),
 *          it's much easier to focus
 *          on those tasks and
 *          <u>
 *              prevent the logic of one task
 *              from becoming interleaved with another
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
 *          The correct response
 *          is to move the code to its own class
 *          (or a utility class)
 *          so it can be used anywhere you like
 *          (see, for example, the 
 *          <a href="#ImagePart">ImagePart</a>
 *          class below).
 *      </li>
 *      <li>
 *          <strong>Informal Testing</strong><br>
 *          Suppose you're concentrating on the artwork
 *          for your hanged man.
 *          If your artwork logic
 *          is interleaved with the rest of your code,
 *          and you're trying to get 
 *          the details of the right arm correct,
 *          you might have to do something like this:
 *          <ul>
 *              <li>Start the application,</li>
 *              <li>Select a word to guess,</li>
 *              <li>Make five consecutive incorrect guesses,</li>
 *              <li>See that you don't like how the right arm is drawn,</li>
 *              <li>Change a 3 to a 4 in your code,<li> and
 *              <li>Start again from the top. (And again, and again.)</li>
 *          </ul>
 *          If you restrict your drawing to a single class,
 *          you can put a short main method in that class
 *          and quickly see the result of editing your code.
 *      </li>
 *      <li>
 *          <strong>Formal Testing</strong><br>
 *          As a developer,
 *          it is your job
 *          to test your code
 *          and make sure it works.
 *          If your project has a dedicated test group,
 *          their job is to concentrate
 *          on the external specification,
 *          not to make sure that 
 *          you close a file after you're finished with it,
 *          or to find out 
 *          that you've got an off-by-one error
 *          in a for loop.
 *          You will probably have
 *          at least one automated test
 *          for each of your classes.
 *          When a class is responsible 
 *          for a limited number of tasks
 *          (preferably <em>one</em>)
 *          it becomes easier to formulate 
 *          a test strategy that comprehensively 
 *          addresses all the code in that class.
 *      </li>
 * </ul>
 * 
 * <p>
 * The classes in my Hangman implementation
 * are as follows.
 * </p>
 * <h4>The WordSelector Class</h4>
 * <p>
 * This class is responsible exclusively
 * for handling the secret word logic,
 * which is more than just
 * posting an input dialog.
 * Other responsibilities include:
 * <ul>
 *      <li>
 *          Detecting when the operator
 *          wants to enter a custom.
 *      </li>
 *      <li>
 *          Detecting when the operator
 *          cancels category selection.
 *      </li>
 *      <li>
 *          Detecting when the operator,
 *          while entering a custom word,
 *          cancels word selection.
 *      </li>
 *      <li>
 *          Deciding what to do when the operator,
 *          while entering a custom word,
 *          responds with an empty string.
 *      </li>
 *      <li>
 *          Trimming extra spaces from the operator's custom word and
 *          (in the case of my implementation)
 *          converting the word to upper case.
 *      </li>
 *      <li>
 *          Finding the file associated with the selected category
 *          (and being prepared for what to do
 *          if the file can't be found).
 *      </li>
 *      <li>
 *          Reading the file associated with the selected category
 *          and handling I/O errors.
 *      </li>
 *      <li>
 *          Filtering duplicate entries from the input file.
 *      </li>
 * </ul>
 * 
 * <h4 id="GuessManager">The GuessManager class</h4>
 * <p>
 * This class will examine a players guess,
 * and determine whether the guess is correct or not.
 * It has to distinguish between a guess of a single letter
 * (the player is guessing whether
 * a particular letter occurs in the secret word)
 * and a guess of multiple letters
 * (the player is trying to guess the secret word).
 * Finally,
 * it will maintain a record
 * of which letters have been correctly guessed.
 * It does this by maintaining a char array
 * with the guessed letters filled in.
 * For example,
 * if the secret word is HEARSE,
 * and the player has guessed E and R,
 * the array will look something like
 * <em>[_E_R_E]</em>.
 * 
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

