package org.zhekehz.stpjava;

public enum Kind {
    UNDEFINED,      //  An undefined expression.
    SYMBOL,         //  Named expression (or variable), i.e. created via 'vc_varExpr'.
    BVCONST,        //  Bitvector constant expression, i.e. created via 'vc_bvConstExprFromInt'.
    BVNOT,          //  Bitvector bitwise-not
    BVCONCAT,       //  Bitvector concatenation
    BVOR,           //  Bitvector bitwise-or
    BVAND,          //  Bitvector bitwise-and
    BVXOR,          //  Bitvector bitwise-xor
    BVNAND,         //  Bitvector bitwise not-and; OR nand (comment: does this still exist?)
    BVNOR,          //  Bitvector bitwise not-or; OR nor (comment: does this still exist?)
    BVXNOR,         //  Bitvector bitwise not-xor; OR xnor (comment: does this still exist?)
    BVEXTRACT,      //  Bitvector extraction, i.e. via 'vc_bvExtract'.
    BVLEFTSHIFT,    //  Bitvector left-shift
    BVRIGHTSHIFT,   //  Bitvector right-right
    BVSRSHIFT,      //  Bitvector signed right-shift
    BVPLUS,         //  Bitvector addition
    BVSUB,          //  Bitvector subtraction
    BVUMINUS,       //  Bitvector unary minus; OR negate expression
    BVMULT,         //  Bitvector multiplication
    BVDIV,          //  Bitvector division
    BVMOD,          //  Bitvector modulo operation
    SBVDIV,         //  Signed bitvector division
    SBVREM,         //  Signed bitvector remainder
    SBVMOD,         //  Signed bitvector modulo operation
    BVSX,           //  Bitvector signed extend
    BVZX,           //  Bitvector zero extend
    ITE,            //  If-then-else
    BOOLEXTRACT,    //  Bitvector boolean extraction
    BVLT,           //  Bitvector less-than
    BVLE,           //  Bitvector less-equals
    BVGT,           //  Bitvector greater-than
    BVGE,           //  Bitvector greater-equals
    BVSLT,          //  Signed bitvector less-than
    BVSLE,          //  Signed bitvector less-equals
    BVSGT,          //  Signed bitvector greater-than
    BVSGE,          //  Signed bitvector greater-equals
    EQ,             //  Equality comparator
    FALSE,          //  Constant false boolean expression
    TRUE,           //  Constant true boolean expression
    NOT,            //  Logical-not boolean expression
    AND,            //  Logical-and boolean expression
    OR,             //  Logical-or boolean expression
    NAND,           //  Logical-not-and boolean expression (comment: Does this still exist?)
    NOR,            //  Logical-not-or boolean expression (comment: Does this still exist?)
    XOR,            //  Logical-xor (either-or) boolean expression
    IFF,            //  If-and-only-if boolean expression
    IMPLIES,        //  Implication boolean expression
    PARAMBOOL,      //  Parameterized boolean expression
    READ,           //  Array read expression
    WRITE,          //  Array write expression
    ARRAY,          //  Array creation expression
    BITVECTOR,      //  Bitvector creation expression
    BOOLEAN;        //  Boolean creation expression

    public static Kind fromInt(int i) {
        if (i >= Kind.values().length) {
            throw new IllegalArgumentException("Kind[" + i + "]");
        }
        return Kind.values()[i];
    }

}
