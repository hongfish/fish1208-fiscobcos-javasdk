pragma solidity ^0.4.24;
import "./Table.sol";

contract KVPerson {
    event SetResult(int256 count);

    KVTableFactory tableFactory;
    string constant TABLE_NAME = "tx_kvperson";

    constructor() public{
        tableFactory = KVTableFactory(0x1010);
        tableFactory.createTable(TABLE_NAME, "id", "name,age,sex");
    }

    function get(string id) public view returns (bool, string, int256, string){
        KVTable table = tableFactory.openTable(TABLE_NAME);
        bool ok = false;
        Entry entry;
        (ok, entry) = table.get(id);
        string memory name;
        int256 age;
        string memory sex;
        if(ok){
            name = entry.getString("name");
            age = entry.getInt("age");
            sex = entry.getString("sex");
        }
        return (ok, name, age, sex);
    }

    function set(string id, string name, int256 age, string sex) public returns (int256){
        KVTable table = tableFactory.openTable(TABLE_NAME);
        Entry entry = table.newEntry();
        entry.set("id", id);
        entry.set("name", name);
        entry.set("age", age);
        entry.set("sex", sex);
        int256 count = table.set(id, entry);
        emit SetResult(count);
        return count;
    }

}
