var fs = require('fs');

let list = require('./list.json');

for (str in list) {
	console.log(
		`"block.betterminecraft.${list[str]}_vertical_slab": "${(
			list[str] + '_vertical_slab'
		)
			.split('_')
			.map((word) => word.charAt(0).toUpperCase() + word.slice(1))
			.join(' ')}",`
	);
}
