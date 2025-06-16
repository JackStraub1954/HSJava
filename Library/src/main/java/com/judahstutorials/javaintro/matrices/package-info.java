/**
 * This package encapsulates a library for performing matrix operations.
 * It is a sample solution to the
 * <a href="https://docs.google.com/document/d/1ZuEtPIaCccuy0YIukFU0BvMWIKWaOLN4AI-RerB3_4Q/edit?tab=t.0#heading=h.8zxu412l5yzd">
 *     Matrix Arithmetic
 * </a>
 * recommended project.
 * It performs addition, subtraction, scalar multiplication, 
 * and dot-product operations,
 * and calculates determinants.
 * It does not calculate inverses.
 * <p>
 * The implementation is absolutely immutable:
 * <ul>
 *      <li>
 *          The original data array,
 *          provided to the constructor,
 *          is copied.
 *          Modifications to the original array
 *          will not affect the data 
 *          encapsulated in the Matrix object.
 *      </li>
 *      <li>
 *          If the user requests the data array comprising the matrix,
 *          or a row therein,
 *          a copy of the data is returned.
 *          Modification to the returned array
 *          will not affect the data encapsulated in the matrix object.
 *      </li>
 *      <li>
 *          The result of any matrix operation that yields another matrix 
 *          is stored in a new Matrix object.
 *          No data is modified directly
 *          in the operand(s) of the operation.
 *      </li>
 * </ul>
 * <p>
 * The Javadoc for the sample solution
 * can be found in the 
 * <a href="https://judahstutorials.com/Classes/JavaClass/downloads/doc/">
 *     Java Intro library documentation.
 * </a>
 */
package com.judahstutorials.javaintro.matrices;