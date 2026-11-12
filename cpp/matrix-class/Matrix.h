#pragma once
#include <string>

class Matrix{
    private: 
        unsigned int m_;
        unsigned int n_;
        int** data_;

    public:
        /**
         * creates a matrix of size (m,n) object stored in a dynamically allocated 2D array
         * and initalizes every position with zeros
         * @param m the number of rows (read-only)
         * @param n the number of columns (read-only)
         */
        Matrix(unsigned int m , unsigned int n);

        /**
         * Frees the memory used by the matrix, first each
         * rows array and then the array of row pointers and stops data_ 
         * from pointing the now freed memory and calls it automatically when 
         * the object goes out of scope
         */
        ~Matrix();

        /**
         * Creates a new matrix from another matrix passed through the constructors
         * parameters, this new matrix is a deep copy, meaning it has its own
         * allocated memory so changed made to it wont effect the original 
         * @param mat a matrix object used to make the deep copy (read-only)
         */
        Matrix(const Matrix& mat);

        /**
         * replaces this matrix values with a copy of mat incl shape and data,
         * The old memory is freed first and the size changes to match mat, then mats data is 
         * copied to the resulting matrix plus returns a reference to itself so x = y = z is possible
         * @param mat the matrix to copy from (read-only)
         * @return a reference to this matrix
         */
        Matrix& operator=(const Matrix& mat);

        /**
         * takes in a 2D array, creates an object of size(m,n),
         * loops over the array and the matrix to copy in
         * the values from the array and doesnt free the array.
         * @param array 2D array to copy from (read-only)
         * @param m the number of rows (read-only)
         * @param n the number of columns (read-only)
         * @throws invalid_argument if array is NULL
         */
        Matrix(int** array, unsigned int m, unsigned int n);

        /**
         * returns the amount of rows in the matrix
         * @return unsigned int of m rows
         */
        unsigned int rows() const;

        /**
         * returns the amount of columns in the matrix
         * @return unsigned int of n columns
         */
        unsigned int columns() const;

        /**
         * takes in an index of row position and j postion based on zeroed
         * indexing, i must be less than rows() and j less than columns().
         * @param i the row index (read-only)
         * @param j the column index (read-only)
         * @return int value stored at (i,j)
         * @throws out_of_range if i >= m or j >= n
         */
        int get(unsigned i , unsigned j) const;

        /**
         * takes in an index of row position i and column position j based on zeroed
         * indexing and sets the value at that position in the matrix to the value passed through.
         * @param i the row index (read-only)
         * @param j the column index (read-only)
         * @param value the int to store at (i,j) (read-only)
         * @throws out_of_range if i >= m or j >= n
         */
        void set (unsigned i, unsigned j, int value);

        /**
         * adds mat to this matrix value by value and returns the result as a new
         * matrix, neither of the original matrices are changed.
         * @param mat the matrix to add to this one (read-only)
         * @return a new matrix of size(m,n) holding the sum
         * @throws invalid_argument if the two matrices are not the same size
         */
        Matrix operator +(const Matrix& mat);

        /**
         * subtracts mat from this matrix value by value and returns the result as a new
         * matrix, neither of the original matrices are changed.
         * @param mat the matrix to subtract from this one (read-only)
         * @return a new matrix of size(m,n) holding the difference
         * @throws invalid_argument if the two matrices are not the same size
         */
        Matrix operator -(const Matrix& mat);

        /**
         * multiplies this matrix by mat using standard matrix multiplication,
         * each value (i,j) in the result is the sum of row i of this matrix 
         * times column j of mat, neither matrix is changed.
         * @param mat the matrix to multiply this one by (read-only)
         * @return a new matrix with m rows and the same number of columns as mat
         * @throws invalid_argument if the columns of this matrix dont equal the rows of mat
         */
        Matrix operator *(const Matrix& mat);

        /**
         * returns the transpose of the matrix so the rows become the columns and
         * the columns become the rows, the original matrix is not changed.
         * @return a new matrix of size(n,m)
         */
        Matrix operator ~() const;

        /**
         * checks if this matrix and mat are equal, first compares the shape and then
         * loops over every value and returns false as soon as two dont match.
         * @param mat the matrix to compare against (read-only)
         * @return true if both have the same shape and values, false otherwise
         */
        bool operator ==(const Matrix& mat);

        /**
         * loops over the matrix and builds a string of it, each value in a row is
         * followed by a space and each row ends with a new line.
         * @return string of the matrix
         */
        std::string toStr();
};