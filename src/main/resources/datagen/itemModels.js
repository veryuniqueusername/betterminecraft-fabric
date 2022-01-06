var fs = require('fs');

let list = require('./list.json');

let template = {
	parent: 'betterminecraft:block/str_vertical_slab',
};
for (str in list) {
	let json = JSON.stringify(template).replace(/str/g, list[str]);
	console.log(json);
	fs.writeFile(
		`./models/item/${list[str]}_vertical_slab.json`,
		json,
		function (err, result) {
			if (err) console.log('error', err);
		}
	);
}
