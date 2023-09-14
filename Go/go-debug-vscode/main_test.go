package main

import "testing"

func Test_add(t *testing.T) {
	a, b, c := 1, 2, 3

	res := add(a, b)

	if res != c {
		t.Fail()
	}
}
