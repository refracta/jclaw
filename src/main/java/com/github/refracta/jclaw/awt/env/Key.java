package com.github.refracta.jclaw.awt.env;

public class Key {
    public static final String SPACE = " ";
    public static final String ENTER = "\n";
    public static final String BACKSPACE = "\b";
    public static final String TAB = "\t";
    public static final String ESC = "\u001b";
    public static final char C_ESC = '\u001b';
    public static final String UP = "\ue000";
    public static final char C_UP = '\ue000';
    public static final String RIGHT = "\ue001";
    public static final char C_RIGHT = '\ue001';
    public static final String DOWN = "\ue002";
    public static final char C_DOWN = '\ue002';
    public static final String LEFT = "\ue003";
    public static final char C_LEFT = '\ue003';
    public static final String PAGE_UP = "\ue004";
    public static final char C_PAGE_UP = '\ue004';
    public static final String PAGE_DOWN = "\ue005";
    public static final char C_PAGE_DOWN = '\ue005';
    public static final String DELETE = "\ue006";
    public static final char C_DELETE = '\ue006';
    public static final String END = "\ue007";
    public static final char C_END = '\ue007';
    public static final String HOME = "\ue008";
    public static final char C_HOME = '\ue008';
    public static final String INSERT = "\ue009";
    public static final char C_INSERT = '\ue009';
    public static final String F1 = "\ue011";
    public static final char C_F1 = '\ue011';
    public static final String F2 = "\ue012";
    public static final char C_F2 = '\ue012';
    public static final String F3 = "\ue013";
    public static final char C_F3 = '\ue013';
    public static final String F4 = "\ue014";
    public static final char C_F4 = '\ue014';
    public static final String F5 = "\ue015";
    public static final char C_F5 = '\ue015';
    public static final String F6 = "\ue016";
    public static final char C_F6 = '\ue016';
    public static final String F7 = "\ue017";
    public static final char C_F7 = '\ue017';
    public static final String F8 = "\ue018";
    public static final char C_F8 = '\ue018';
    public static final String F9 = "\ue019";
    public static final char C_F9 = '\ue019';
    public static final String F10 = "\ue01a";
    public static final char C_F10 = '\ue01a';
    public static final String F11 = "\ue01b";
    public static final char C_F11 = '\ue01b';
    public static final String F12 = "\ue01c";
    public static final char C_F12 = '\ue01c';
    public static final String F13 = "\ue01d";
    public static final char C_F13 = '\ue01d';
    public static final String F14 = "\ue01e";
    public static final char C_F14 = '\ue01e';
    public static final String F15 = "\ue01f";
    public static final char C_F15 = '\ue01f';
    public static final String SHIFT = "\ue020";
    public static final char C_SHIFT = '\ue020';
    public static final String CTRL = "\ue021";
    public static final char C_CTRL = '\ue021';
    public static final String ALT = "\ue022";
    public static final char C_ALT = '\ue022';
    public static final String META = "\ue023";
    public static final char C_META = '\ue023';
    public static final String CMD = "\ue023";
    public static final char C_CMD = '\ue023';
    public static final String WIN = "\ue023";
    public static final char C_WIN = '\ue023';
    public static final String PRINTSCREEN = "\ue024";
    public static final char C_PRINTSCREEN = '\ue024';
    public static final String SCROLL_LOCK = "\ue025";
    public static final char C_SCROLL_LOCK = '\ue025';
    public static final String PAUSE = "\ue026";
    public static final char C_PAUSE = '\ue026';
    public static final String CAPS_LOCK = "\ue027";
    public static final char C_CAPS_LOCK = '\ue027';
    public static final String NUM0 = "\ue030";
    public static final char C_NUM0 = '\ue030';
    public static final String NUM1 = "\ue031";
    public static final char C_NUM1 = '\ue031';
    public static final String NUM2 = "\ue032";
    public static final char C_NUM2 = '\ue032';
    public static final String NUM3 = "\ue033";
    public static final char C_NUM3 = '\ue033';
    public static final String NUM4 = "\ue034";
    public static final char C_NUM4 = '\ue034';
    public static final String NUM5 = "\ue035";
    public static final char C_NUM5 = '\ue035';
    public static final String NUM6 = "\ue036";
    public static final char C_NUM6 = '\ue036';
    public static final String NUM7 = "\ue037";
    public static final char C_NUM7 = '\ue037';
    public static final String NUM8 = "\ue038";
    public static final char C_NUM8 = '\ue038';
    public static final String NUM9 = "\ue039";
    public static final char C_NUM9 = '\ue039';
    public static final String SEPARATOR = "\ue03a";
    public static final char C_SEPARATOR = '\ue03a';
    public static final String NUM_LOCK = "\ue03b";
    public static final char C_NUM_LOCK = '\ue03b';
    public static final String ADD = "\ue03c";
    public static final char C_ADD = '\ue03c';
    public static final String MINUS = "\ue03d";
    public static final char C_MINUS = '\ue03d';
    public static final String MULTIPLY = "\ue03e";
    public static final char C_MULTIPLY = '\ue03e';
    public static final String DIVIDE = "\ue03f";
    public static final char C_DIVIDE = '\ue03f';

    public Key() {
    }

    public static int[] toJavaKeyCode(String key) {
        return key.length() > 0 ? toJavaKeyCode(key.charAt(0)) : null;
    }

    public static int[] toJavaKeyCode(char key) {
        switch (key) {
            case '\b':
                return new int[]{8};
            case '\t':
                return new int[]{9};
            case '\n':
                return new int[]{10};
            case '\r':
                return new int[]{10};
            case '\u001b':
                return new int[]{27};
            case ' ':
                return new int[]{32};
            case '!':
                return new int[]{16, 49};
            case '"':
                return new int[]{16, 222};
            case '#':
                return new int[]{16, 51};
            case '$':
                return new int[]{16, 52};
            case '%':
                return new int[]{16, 53};
            case '&':
                return new int[]{16, 55};
            case '\'':
                return new int[]{222};
            case '(':
                return new int[]{16, 57};
            case ')':
                return new int[]{16, 48};
            case '*':
                return new int[]{16, 56};
            case '+':
                return new int[]{16, 61};
            case ',':
                return new int[]{44};
            case '-':
                return new int[]{45};
            case '.':
                return new int[]{46};
            case '/':
                return new int[]{47};
            case '0':
                return new int[]{48};
            case '1':
                return new int[]{49};
            case '2':
                return new int[]{50};
            case '3':
                return new int[]{51};
            case '4':
                return new int[]{52};
            case '5':
                return new int[]{53};
            case '6':
                return new int[]{54};
            case '7':
                return new int[]{55};
            case '8':
                return new int[]{56};
            case '9':
                return new int[]{57};
            case ':':
                return new int[]{16, 59};
            case ';':
                return new int[]{59};
            case '<':
                return new int[]{16, 44};
            case '=':
                return new int[]{61};
            case '>':
                return new int[]{16, 46};
            case '?':
                return new int[]{16, 47};
            case '@':
                return new int[]{16, 50};
            case 'A':
                return new int[]{16, 65};
            case 'B':
                return new int[]{16, 66};
            case 'C':
                return new int[]{16, 67};
            case 'D':
                return new int[]{16, 68};
            case 'E':
                return new int[]{16, 69};
            case 'F':
                return new int[]{16, 70};
            case 'G':
                return new int[]{16, 71};
            case 'H':
                return new int[]{16, 72};
            case 'I':
                return new int[]{16, 73};
            case 'J':
                return new int[]{16, 74};
            case 'K':
                return new int[]{16, 75};
            case 'L':
                return new int[]{16, 76};
            case 'M':
                return new int[]{16, 77};
            case 'N':
                return new int[]{16, 78};
            case 'O':
                return new int[]{16, 79};
            case 'P':
                return new int[]{16, 80};
            case 'Q':
                return new int[]{16, 81};
            case 'R':
                return new int[]{16, 82};
            case 'S':
                return new int[]{16, 83};
            case 'T':
                return new int[]{16, 84};
            case 'U':
                return new int[]{16, 85};
            case 'V':
                return new int[]{16, 86};
            case 'W':
                return new int[]{16, 87};
            case 'X':
                return new int[]{16, 88};
            case 'Y':
                return new int[]{16, 89};
            case 'Z':
                return new int[]{16, 90};
            case '[':
                return new int[]{91};
            case '\\':
                return new int[]{92};
            case ']':
                return new int[]{93};
            case '^':
                return new int[]{16, 54};
            case '_':
                return new int[]{16, 45};
            case '`':
                return new int[]{192};
            case 'a':
                return new int[]{65};
            case 'b':
                return new int[]{66};
            case 'c':
                return new int[]{67};
            case 'd':
                return new int[]{68};
            case 'e':
                return new int[]{69};
            case 'f':
                return new int[]{70};
            case 'g':
                return new int[]{71};
            case 'h':
                return new int[]{72};
            case 'i':
                return new int[]{73};
            case 'j':
                return new int[]{74};
            case 'k':
                return new int[]{75};
            case 'l':
                return new int[]{76};
            case 'm':
                return new int[]{77};
            case 'n':
                return new int[]{78};
            case 'o':
                return new int[]{79};
            case 'p':
                return new int[]{80};
            case 'q':
                return new int[]{81};
            case 'r':
                return new int[]{82};
            case 's':
                return new int[]{83};
            case 't':
                return new int[]{84};
            case 'u':
                return new int[]{85};
            case 'v':
                return new int[]{86};
            case 'w':
                return new int[]{87};
            case 'x':
                return new int[]{88};
            case 'y':
                return new int[]{89};
            case 'z':
                return new int[]{90};
            case '{':
                return new int[]{16, 91};
            case '|':
                return new int[]{16, 92};
            case '}':
                return new int[]{16, 93};
            case '~':
                return new int[]{16, 192};
            case '\ue000':
                return new int[]{38};
            case '\ue001':
                return new int[]{39};
            case '\ue002':
                return new int[]{40};
            case '\ue003':
                return new int[]{37};
            case '\ue004':
                return new int[]{33};
            case '\ue005':
                return new int[]{34};
            case '\ue006':
                return new int[]{127};
            case '\ue007':
                return new int[]{35};
            case '\ue008':
                return new int[]{36};
            case '\ue009':
                return new int[]{155};
            case '\ue011':
                return new int[]{112};
            case '\ue012':
                return new int[]{113};
            case '\ue013':
                return new int[]{114};
            case '\ue014':
                return new int[]{115};
            case '\ue015':
                return new int[]{116};
            case '\ue016':
                return new int[]{117};
            case '\ue017':
                return new int[]{118};
            case '\ue018':
                return new int[]{119};
            case '\ue019':
                return new int[]{120};
            case '\ue01a':
                return new int[]{121};
            case '\ue01b':
                return new int[]{122};
            case '\ue01c':
                return new int[]{123};
            case '\ue01d':
                return new int[]{61440};
            case '\ue01e':
                return new int[]{61441};
            case '\ue01f':
                return new int[]{61442};
            case '\ue020':
                return new int[]{16};
            case '\ue021':
                return new int[]{17};
            case '\ue022':
                return new int[]{18};
            case '\ue023':
                return new int[]{157};
            case '\ue024':
                return new int[]{154};
            case '\ue025':
                return new int[]{145};
            case '\ue026':
                return new int[]{19};
            case '\ue027':
                return new int[]{20};
            case '\ue030':
                return new int[]{96};
            case '\ue031':
                return new int[]{97};
            case '\ue032':
                return new int[]{98};
            case '\ue033':
                return new int[]{99};
            case '\ue034':
                return new int[]{100};
            case '\ue035':
                return new int[]{101};
            case '\ue036':
                return new int[]{102};
            case '\ue037':
                return new int[]{103};
            case '\ue038':
                return new int[]{104};
            case '\ue039':
                return new int[]{105};
            case '\ue03a':
                return new int[]{108};
            case '\ue03b':
                return new int[]{144};
            case '\ue03c':
                return new int[]{107};
            case '\ue03d':
                return new int[]{45};
            case '\ue03e':
                return new int[]{106};
            case '\ue03f':
                return new int[]{111};
            default:
                throw new IllegalArgumentException("Cannot convert character " + key);
        }
    }
}
