//modules ONLY. Not a class for USE. 

@EventHandler
    public void onSignSet(SignChangeEvent event){
        if(event.getLine(0).contains("[randomtp]")) {
            event.setLine(0, ChatColor.GREEN + "" + ChatColor.BOLD + "Random Teleport!");
        }
    }

    @EventHandler
    public void onSignInteract(PlayerInteractEvent event){
        Player p = event.getPlayer();
        if(event.getAction() == Action.RIGHT_CLICK_BLOCK) {
        
            Block block = event.getClickedBlock();
            
            if(block.getType() == Material.SIGN || 
                block.getType() == Material.SIGN_POST ||
                block.getType() == Material.WALL_SIGN){
                
                Sign sign = (Sign) event.getClickedBlock().getState();
                
                if(sign.getLine(0).contains("Random Teleport!")){
                    Random r = new Random();
                    double x = r.nextDouble() + 1;
                    double y = p.getWorld().getMaxHeight();
                    double z = r.nextDouble() + 1;
                    Location loc = new Location(p.getWorld(), x,y,z);
                    p.teleport(loc);
                }
            }
        }
    }
