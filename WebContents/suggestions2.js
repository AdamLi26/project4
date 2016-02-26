
/**
 * Provides suggestions for state names (USA).
 * @class
 * @scope public
 */

function StateSuggestions(httprequest) {
    this.states = [];
    this.httpRequest = httprequest;
    this.sTextboxValue = null;
    this.suggestionControl=null;
    this.typeAhead=null;
}

/**
 * Request suggestions for the given autosuggest control. 
 * @scope protected
 * @param oAutoSuggestControl The autosuggest control to provide suggestions for.
 */
StateSuggestions.prototype.requestSuggestions = function (oAutoSuggestControl /*:AutoSuggestControl*/,
                                                          bTypeAhead /*:boolean*/) {
    this.sTextboxValue = oAutoSuggestControl.textbox.value;
    this.suggestionControl = oAutoSuggestControl;
    this.typeAhead = bTypeAhead;

    var request = "suggest?q="+encodeURI(this.sTextboxValue);
    this.httpRequest.open("GET", request);
    this.httpRequest.onreadystatechange = this.callBack.bind(this);
    this.httpRequest.send(null);
    
    
};

StateSuggestions.prototype.callBack = function(){
    if (xmlHttp.readyState == 4) {
        console.log(this.httpRequest.responseXML);
        var s = this.httpRequest.responseXML.getElementsByTagName('CompleteSuggestion');
        var aSuggestions = [];
        this.states = [];
        for (i = 0; i < s.length; i++) {
            this.states.push(s[i].childNodes[0].getAttribute("data"));
        }

        if (this.sTextboxValue.length > 0){
    
            //search for matching states
            for (var i=0; i < this.states.length; i++) { 
                if (this.states[i].indexOf(this.sTextboxValue) == 0) {
                    aSuggestions.push(this.states[i]);
                } 
            }
        }

        //provide suggestions to the control
        this.suggestionControl.autosuggest(aSuggestions, this.typeAhead);
    }
}

