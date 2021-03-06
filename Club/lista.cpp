#include <iostream>

using namespace std;

template<class T> struct LinkedList {

  struct Node {
    Node * next;
    T data;
    Node(T val) {
      next = NULL;
      data = val;
    }
  };

  Node * head;
  Node * tail;
  int size;

  LinkedList() {
    tail = head = NULL;
    size = 0;
  }

  void push_back(T val) {
    Node * node = new Node(val);
    if (size == 0) {
      tail = head = node;
    }
    else {
      tail->next = node;
      tail = node;
    }
    size++;
  }

  void pop_back() {
    if (size == 0) {
      throw runtime_error("Cannot erase from the empty list");
    }
    if (size == 1) {
      head = tail = NULL;
    }
    else {
      Node * prev = head;
      Node * last = head->next;
      while (last && last->next) {
        last = last->next;
        prev = prev->next;
      }
      prev->next = NULL;
      tail = prev;
    }
    size--;
  }

  bool exists(T val) {
    if (size == 0) {
      return false;
    }
    Node * current = head;
    while (current) {
      if (current->data == val) {
        return true;
      }
      current = current->next;
    }
    return false;
  }
};

int main() {
  LinkedList<int> * list = new LinkedList<int>();
  list->push_back(1);
  if (list->exists(1))
    cout << ":)" << endl;
  cout << list->size << endl;
  list->pop_back();
  if (!list->exists(1))
    cout << ":(" << endl;
  cout << list->size << endl;
  return 0;
}