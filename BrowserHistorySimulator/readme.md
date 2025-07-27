📌 Problem: Design a Browser History System
You are to implement a class BrowserHistory that simulates a browser's navigation functionality. The system must support visiting URLs, going back a certain number of steps in the browsing history, and moving forward after going back.

🧱 Class Definition
```
class BrowserHistory {
    public BrowserHistory(String homepage);
    public void visit(String url);
    public String back(int steps);
    public String forward(int steps);
}
```
🔧 Function Details

```
BrowserHistory(String homepage)
```
Initializes the object with the homepage of the browser. 

```
void visit(String url)
```
Visits url from the current page.

It clears all the forward history.

```
String back(int steps)

```
Move steps steps back in history.

If you can only go x steps back, go back x steps.

Return the current page after moving.

```
String forward(int steps)
```

Move steps steps forward in history.

If you can only go y steps forward, go forward y steps.

Return the current page after moving.

Example:
```
BrowserHistory bh = new BrowserHistory("google.com");
bh.visit("leetcode.com");      // google -> leetcode
bh.visit("github.com");        // leetcode -> github
bh.visit("stackoverflow.com"); // github -> stackoverflow

bh.back(1); // returns "github.com"
bh.back(1); // returns "leetcode.com"
bh.forward(1); // returns "github.com"
bh.visit("openai.com"); // github -> openai (forward history cleared)

bh.forward(2); // returns "openai.com"
bh.back(2); // returns "leetcode.com"
bh.back(7); // returns "google.com"
```