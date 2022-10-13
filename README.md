# ModelDumper

Adds `dump` hotkeys(not bound by default!) to the game that dumps the currently rendered models to a file.
The output file is a `.obj` file that can be opened in any 3D modelling software and the required textures are also dumped. The model is uv mapped, so the textures should be applied correctly. Important: The model is not rigged, it is just a static model in the current pose of the game.
Currently works with:

- Players
- Entities

Everything connected to the player or entities like armor, held items, capes, 3d skin layers, etc. is also dumped.
Files will be located in the `ModelDumps` folder in the game directory.

## Dependencies

- none

Support via [![Discord](https://tr7zw.dev/curse/Discord.png)](https://discord.gg/2wKH8yeThf) or [Github](https://github.com/tr7zw/modeldumper)!

## License

This project is licensed under [``tr7zw Protective License``](LICENSE).
This license does not allow others to distribute the software/derivative works(in source or binary form).
You have to contact the author to get permission for redistribution. (For example: Modpacks(that are not hosted on CurseForge), "Clients", mod hosting sites).
Keep in mind that [Githubs TOS](https://docs.github.com/en/github/site-policy/github-terms-of-service#d-user-generated-content) and [Overwolfs TOS](https://www.overwolf.com/legal/terms/) apply at their respective places. This (among other things) means you don't need to ask to include the mod in a CurseForge Modpack and that by contributing code it explicitly gets the same license as the repository.
