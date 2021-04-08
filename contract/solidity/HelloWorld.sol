pragma solidity ^0.4.24;

contract HelloWorld {
    string name;
    bytes32 _userName;

    constructor() public {
        name = "Hello, World!";
    }

    function get() public view returns (string) {
        return name;
    }

    function set(string n) public {
        name = n;
    }

    function modifyUserName(bytes32 userName) public returns(bytes32){
        _userName = userName;
        return _userName;
    }
}
