console.log("Starting bot");

var config = {
        channels: ["#yourchannel"],
        server: "your.irc.server.com",
        botName: "BotName"
};
var irc = require("irc");
if(irc == null)
{
        console.log("ifc failed");
}
var bot = new irc.Client(config.server, config.botName, {
        channels: config.channels
});


bot.addListener("connect", function(from, to, text, message) {
        console.log("Connected");
        //bot.say(config.channels[0], "¿Que?");
});

bot.addListener("message", function(from, to, message){
        console.log(from +  " => " + to + ": " + message);
});

bot.addListener('pm', function(from, message) {
        console.log(from + ' => ME: ' + message);
});

bot.addListener('error', function(message) {
    console.log('error: ', message);
});
