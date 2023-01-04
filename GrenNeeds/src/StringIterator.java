//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

class StringIterator {
    protected String s;
    protected int index;

    StringIterator() {
    }

    public void setString(String var1) {
        this.s = var1;
        this.index = 0;
    }

    public String nextWord(char var1) {
        while(this.index < this.s.length() && this.s.charAt(this.index) == var1) {
            ++this.index;
        }

        int var2;
        for(var2 = this.index; this.index < this.s.length() && this.s.charAt(this.index) != var1; ++this.index) {
        }

        if (var2 == this.index) {
            return null;
        } else {
            return this.s.substring(var2, this.index);
        }
    }

    public String nextWord() {
        return this.nextWord(' ');
    }
}
