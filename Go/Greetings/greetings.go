package greetings

import "fmt"

// Hello returns a greeting for the named person.
// function format - <funcName> (paramName <paramType>) <returnType>
func Hello(name string) string {
	// Retun a greeting that embeds the name in a message
	message := fmt.Sprintf("Hi, %v. Welcome!", name)
	return message
}
