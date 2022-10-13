# ModelDumper

[![Discord](https://tr7zw.dev/curse/Discord-long.png)](https://discord.gg/2wKH8yeThf)[![ko-fi](https://ko-fi.com/img/githubbutton_sm.svg)](https://ko-fi.com/O5O7ACGRH)

Adds `dump` hotkeys(not bound by default!) to the game that dumps the currently rendered models to a file.
The output file is a `.obj` file that can be opened in any 3D modeling software and the required textures are also dumped. The model is uv mapped, so the textures should be applied correctly. Important: The model is not rigged, it is just a static model in the current pose of the game.
Currently works with:

- Players
- Entities

Everything connected to the player or entities like armor, held items, capes, 3d skin layers, etc. is also dumped.
Files will be located in the `ModelDumps` folder in the game directory.

## Dependencies

- FabricAPI

Support via [![Discord](https://tr7zw.dev/curse/Discord.png)](https://discord.gg/2wKH8yeThf) or [Github](https://github.com/tr7zw/modeldumper)! The comments are not there to report bugs/crashes/get help.
