#include "Matrix.h"
#include <stdexcept>
#include <string>
#include <sstream>
using namespace std;

Matrix::Matrix(unsigned int m, unsigned int n): m_(m), n_(n) {
    // allocate the array of row pointers
    data_ = new int*[m_];

    // allocate each row, the () sets every value to 0
    for(unsigned int i = 0; i < m_; i++){
        data_[i] = new int[n_]();
    }
}


Matrix::~Matrix(){
  // free each row first then the array of row pointers
  for (unsigned int i = 0; i < m_; i++) {
    delete [] data_[i];
  }
  delete [] data_;
  data_ = nullptr;
}


Matrix::Matrix(const Matrix& mat){
    m_ = mat.m_;
    n_ = mat.n_;

    data_ = new int*[m_];

    for(unsigned int i = 0; i < m_; i++){
        data_[i] = new int[n_];
    }

    // deep copy each value from mat
    for(unsigned int i = 0; i < m_; i++){
        for(unsigned int j = 0; j < n_; j++){
            data_[i][j] = mat.data_[i][j];
        }
    }

}


Matrix& Matrix::operator=(const Matrix& mat){
    // stops self assignment from freeing its own data
    if(this == &mat) return (*this);

    // free the old memory before resizing to mats shape
    for (unsigned int i = 0; i < m_; i++) {
        delete [] data_[i];
    }
    delete [] data_;
    data_ = nullptr;

    m_ = mat.m_;
    n_ = mat.n_;

    data_ = new int*[m_];

    for(unsigned int i = 0; i < m_; i++){
        data_[i] = new int[n_];
    }

    // deep copy each value from mat
    for(unsigned int i = 0; i < m_; i++){
        for(unsigned int j = 0; j < n_; j++){
            data_[i][j] = mat.data_[i][j];
        }
    }

    // return a reference to itself so x = y = z works
    return (*this);
} 


Matrix::Matrix(int** array, unsigned int m, unsigned int n): m_(m), n_(n){
    // cant copy from a NULL array
    if(array == NULL) throw invalid_argument("invalid argument passed through");
    data_ = new int*[m_];

    for(unsigned int i = 0; i < m_; i++){
        data_[i] = new int[n_];
    }

    // copy each value over from the array
    for(unsigned int i = 0; i < m_; i++){
        for(unsigned int j = 0; j < n_; j++){
            data_[i][j] = array[i][j];
        }
    }
}


unsigned int Matrix::rows() const{
    return m_;
}


unsigned int Matrix::columns() const{
    return n_;
}


int Matrix::get(unsigned i , unsigned j) const{
    if(i >= m_ || j >= n_) throw out_of_range("either i is greater or equal to m, or j is greater than or equal to n");
    
    return data_[i][j];
}


void Matrix::set(unsigned i, unsigned j, int value){
    if(i >= m_ || j >= n_) throw out_of_range("either i is greater or equal to m, or j is greater than or equal to n");

    data_[i][j] = value;
}


Matrix Matrix::operator +(const Matrix& mat){
    if (m_ != mat.m_ || n_ != mat.n_) throw invalid_argument("cannot add matricies of two different sizes");
    Matrix res(m_, n_);

    for (unsigned int i = 0; i < m_; i++) {
        for (unsigned int j = 0; j < n_; j++) {
            res.data_[i][j] = data_[i][j] + mat.data_[i][j];
        }
    }

    return res;    
}


Matrix Matrix::operator -(const Matrix& mat){
    if (m_ != mat.m_ || n_ != mat.n_) throw invalid_argument("cannot subtract matricies of two different sizes");
    Matrix res(m_, n_);

    for (unsigned int i = 0; i < m_; i++) {
        for (unsigned int j = 0; j < n_; j++) {
            res.data_[i][j] = data_[i][j] - mat.data_[i][j];
        }
    }

    return res;    
}


Matrix Matrix::operator *(const Matrix& mat){
    if (n_ != mat.m_) throw invalid_argument("cannot multiply matricies");
    // res starts as all 0s so += can be used to build the sum
    Matrix res(m_, mat.n_);

    for (unsigned int i = 0; i < m_; i++) {
        for (unsigned int j = 0; j < mat.n_; j++) {
            // sum of row i of this matrix times column j of mat
            for(unsigned int k = 0; k < n_; k++){
                res.data_[i][j] += data_[i][k] * mat.data_[k][j];
            }
        }
    }

    return res; 
}


Matrix Matrix::operator ~() const{
    // rows and columns are swapped for the transpose
    Matrix res(n_, m_);

    for (unsigned int i = 0; i < m_; i++) {
        for (unsigned int j = 0; j < n_; j++) {
            res.data_[j][i] = data_[i][j];
        }
    }

    return res;  

}


bool Matrix::operator ==(const Matrix& mat){
    // different shapes means they cant be equal
    if (m_ != mat.m_ || n_ != mat.n_) {      
        return false;
    }

    for (unsigned int i = 0; i < m_; i++) {
        for (unsigned int j = 0; j < n_; j++) {
            if (data_[i][j] != mat.data_[i][j]) {   
                return false;
            }
        }
    }

    return true;         
}


string Matrix::toStr(){
    ostringstream ostr;
    
    for(unsigned int i = 0; i < m_; i++){
        for(unsigned int j = 0; j < n_; j++){
            ostr << data_[i][j] << " ";
        }
        // new line at the end of each row
        ostr << "\n";
    }

    return (ostr.str());
}