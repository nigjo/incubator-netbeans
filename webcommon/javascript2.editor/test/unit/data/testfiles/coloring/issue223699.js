var obj = {
    x: 0,
    doSomething: function () {},        
    2: 1,
    metho: function ( ) {
        var a;   
        var q = this['showsAsGlobal'].x; // this shows as Global
        var q2 = this['showsAsGlobal'].x(); // this shows as Global
        this[a].metho();                    
        this[2] = 'bug'; // this shows as Global
        this[2] = this[a]; // left this shows as Global
        this[2] = this[3]; // both this shows as Global
        this[2] = this['bug']; // both this shows as Global
        this[2](); // this shows as Global
        this[a].init = (this['bug']()?0:1); // right shows as Global
        this[a].init( a, bug, this );  // this is blue, but bug variable shouldbe Global, its not defined                    
        this[bug].init( a, bug, this );
        this[bug] = bug; // right bug variable should be Global, its not defined                    
        this['is OK']; // this shows as Global
    }
};
