/**
 * This package contains an application
 * that implements the evolutionary algorithm
 * suggested by 
 * <a href="https://en.wikipedia.org/wiki/Richard_Dawkins">
 *     Richard Dawkins
 * </a>
 * in his book
 * <a href="https://www.amazon.com/Blind-Watchmaker-Evidence-Evolution-Universe-ebook/dp/B014LJE1HI/ref=sr_1_1?crid=VN3X1938S59T&dib=eyJ2IjoiMSJ9.u8WPkG0FBSOGnsvN7jkEw3qq-eVAi9IaY6CytH0-2dEu8RZlYFlyDXUfs8xfAar3nMTYBJisnsJ7n7li95wR2ZMzLd0nOKb4eRd9lQSNIveysN6KrGuzNHxPOYA-kg9iWP4d0RNc8QBA2lq3h4jNOiIuD2Kf7GZ8bxjlwee3zf1OEAKMKMSu6lrYz-qcGri2CvlM8pbmmN_TFfvJL9mXFtDxS4exBqCyX9DDNBkitvM.cIyekAHmro7eDho17MIzZfZts3cB-W8bP1OOXlskw7M&dib_tag=se&keywords=the+blind+watchmaker&qid=1749846349&sprefix=The+blind+wa%2Caps%2C179&sr=8-1">
 *     The Blind Watchmaker.
 * </a>
 * Given:
 *   <ol type = 'a'>
 *      <li>
 *          A gene pool.
 *      </li>
 *      <li>
 *          An individual with a set of randomly selected genes
 *          from the gene pool.
 *      </li>
 *      <li>
 *          The number of children born in a generation.
 *      </li>
 *      <li>
 *          The frequency with which genes mutate.
 *      </li>
 *      <li>
 *          The set of genes desired in an individual.
 *      </li>
 *   </ol>
 *   <p>
 *      Simulate the sequence of generations
 *      needed to produce a child
 *      with the desired set of genes.
 *   </p>
 *   <p>
 *      We take the gene pool to be the capital letters A-Z plus a space.
 *      The desired set of genes is "METHINKS IT IS LIKE A WEASEL."
 *      The number of children born in a generation
 *      and the likelihood of genetic mutation can be anything you like.
 *      The sample solution uses 100 children per generation
 *      and a mutation rate of 5%.
 *   </p>
 *   
 *   @see <a href="https://en.wikipedia.org/wiki/Weasel_program">
 *          Weasel Program
 *        </a>
 *        on Wikipedia
 *  @see <a href="https://docs.google.com/document/d/1fW2heKiNVW5j2H6h1481GixvTkOeXmzbbgv87xPkHH0/edit?tab=t.0#heading=h.6n1l8w7rhvcm">
 *       Dawkins's Weasel
 *       </a>
 *       on the class website.
 */
 package com.judahstutorials.javaintro.dawkinsweasel;
